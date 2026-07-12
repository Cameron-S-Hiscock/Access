package com.cameronsh.core.registry

import com.cameronsh.utils.Id

import com.cameronsh.core.registry.RegistryRepository

object RegistryService {
    private val RegistryRepositroy: RegistryRepository = RegistryRepository(100)
    val id: String = Id.genId()
}
