package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.models.task.Task

class RegistryRepository(
    val taskCap: Int
) {
    internal val id: UUID = Id.genId()
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
}
