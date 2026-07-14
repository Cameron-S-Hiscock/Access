package com.cameronsh.core.launcher

import com.cameronsh.utils.Id

import com.cameronsh.core.launcher.Launcher
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.Controller

object LauncherFactory {
    fun create(): Launcher {
        val launcher = Launcher(RegistryService)
        return launcher
    }
}
