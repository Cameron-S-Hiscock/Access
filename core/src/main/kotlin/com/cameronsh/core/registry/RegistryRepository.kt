package com.cameronsh.core.registry

import com.cameronsh.utils.Id

import com.cameronsh.core.models.task.Task

class RegistryRepository(
    val taskCap: Int
) {
    val id: String = Id.genId()
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
}
