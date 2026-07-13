package com.cameronsh.core.registry

import com.cameronsh.utils.Id

import com.cameronsh.core.models.tasks.Task

class RegistryRepository(
    val tasksCap: Int
) {
    val id: String = Id.genId()
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
}
