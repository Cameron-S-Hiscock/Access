package net.accesstechnologies.core.registry

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.models.tasks.Task

class RegistryRepository(
    val tasksCap: Int
) {
    val id: String = Id.genId()
    val tasks: Array<Task> = arrayOf<Task>()
}