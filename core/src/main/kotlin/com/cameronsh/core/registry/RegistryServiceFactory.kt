package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryService

class RegistryServiceFactory {
    fun create(): RegistryService {
        val registryService = RegistryService()
        return registryService
    }
}
