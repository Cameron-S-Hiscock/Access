package net.accesstechnologies.core.registry

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.registry.RegistryRepository

class RegistryService(
    val RegistryRepositroy: RegistryRepository
) {
    val id: String = Id.genId()
}