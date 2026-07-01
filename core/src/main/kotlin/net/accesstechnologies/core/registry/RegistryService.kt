package net.accesstechnologies.core.registry

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.registry.RegistryRepository

object RegistryService {
    private val RegistryRepositroy: RegistryRepository = RegistryRepository(100)
    val id: String = Id.genId()
}
