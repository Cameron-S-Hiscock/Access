package com.cameronsh.core.registry

import com.cameronsh.utils.Id

import com.cameronsh.core.registry.RegistryRepository

object RegistryService {
    private val registryRepository: RegistryRepository = RegistryRepository(100)
    val id: String = Id.genId()
    
    fun registerTask(task: Task): Boolean {
        val idx = registryRepository.tasks.indexOfFirst { it == null }
        if(idx != -1) {
            registryRepository.tasks[idx] = task
            return true
        } else {
            return false
        }
    }
}
