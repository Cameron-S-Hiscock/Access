package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.systems.IOStreamBridge

object FlowDirector {
    val id: UUID = Id.genId(this)

    val ComposerControllerIOStream = IOStreamBridge.create_iostream(arrayOf(Controller.id, Composer.id))
}
