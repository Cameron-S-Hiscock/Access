package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.iostream.message.MessageFactory
import com.cameronsh.core.bridge.Bridge
import com.cameronsh.core.bridge.BridgeError
import com.cameronsh.core.bridge.BridgeState
import com.cameronsh.core.bridge.BridgeRepository
import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.core.iostream.IOStreamAuthorTable
import com.cameronsh.core.workers.Worker
import kotlinx.coroutines.*

object UICoreBridge: Bridge {
    override val id: UUID = Id.genId(this)
    override val name: String = "UICoreBridge"
    init { Id.objectIds.putIfAbsent("UICoreBridge", id) }

    var state = BridgeState.PENDING

    private val UICoreBridgeProcessWorker = ProcessWorker(
        name = "UICoreBridgeProcessWorker",
        host = id,
    )
    private val UICoreBridgeReceiverWorker = Worker(
        name = "UICoreBridgeReceiverWorker"
    )
    private val UICoreBridgeSenderWorker = Worker(
        name = "UICoreBridgeSenderWorker"
    )
    private val io = IOStream(
        name = "UICoreBridgeIOStream",
        targets = mutableListOf(Composer.id, Controller.id),
    )
    init {
        BridgeRepository.bridges.putIfAbsent("UICoreBridge", this)
        IOStreamAuthorTable.addPair(Composer.id, Controller.id)
    }
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val UICache = LinkedBlockingDeque<Message>()
    val CoreCache = LinkedBlockingDeque<Message>()

    override suspend fun init() = coroutineScope {
        launch {
            UICoreBridgeProcessWorker.run()
        }

        // launch {
        //     UICoreBridgeProcessWorker.submitWork(
        //         UICoreBridgeProcessWorker.taskFactory.create(
        //             name = "UICoreBridgeReceiveMessages",
        //         ) {
        //             while(true) {
        //                 val uiMessage = UIOutCache.pollFirst()
        //                 val coreMessage = CoreOutCache.pollFirst()
        //                 if(uiMessage != null) {
        //                     CoreInCache.offerLast(uiMessage)
        //                 }
        //                 if(coreMessage != null) {
        //                     UIInCache.offerLast(coreMessage)
        //                 }
        //             }
        //         }
        //     )
        // }

        // launch {
        //     UICoreBridgeProcessWorker.submitWork(
        //         UICoreBridgeProcessWorker.taskFactory.create(
        //             name = "UICoreBridgeSendMessages",
        //         ) {
        //             while(true) {
        //                 val uiMessage = UIInCache.pollFirst()
        //                 val coreMessage = CoreInCache.pollFirst()
        //                 if(uiMessage != null) {
        //                     IO.send(author = uiMessage.origin, message = uiMessage)
        //                 }
        //                 if(coreMessage != null) {
        //                     IO.send(author = coreMessage.origin, message = coreMessage)
        //                 }
        //             }
        //         }
        //     )
        // }

        state = BridgeState.OPEN
    }

    override suspend fun send(authorId: UUID, message: Message): Result<Unit> {
        if(state != BridgeState.OPEN) {
            return Result.failure(IllegalArgumentException(BridgeError.InvalidBridgeState(state).toString()))
        }
        if(authorId != Controller.id && authorId != Composer.id) {
            return Result.failure(IllegalArgumentException(BridgeError.InvalidAuthorPair(authorId).toString()))
        }
        when(authorId) {
            Controller.id -> UICache.putLast(message)
            Composer.id -> CoreCache.putLast(message)
        }
        return Result.success(Unit)
    }

    override suspend fun receive(authorId: UUID): Message? {
        if(state != BridgeState.OPEN) {
            return null
        }
        if(authorId != Controller.id && authorId != Composer.id) {
            return null
        }
        return when(authorId) {
            Controller.id -> CoreCache.pollFirst()
            Composer.id -> UICache.pollFirst()
            else -> null
        }
    }
}
