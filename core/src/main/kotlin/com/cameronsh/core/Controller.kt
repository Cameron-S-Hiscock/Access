package com.cameronsh.core

import com.cameronsh.utils.Id

import com.cameronsh.core.registry.*
import com.cameronsh.core.scheduler.*
import com.cameronsh.core.models.tasks.TaskFactory
import com.cameronsh.core.launcher.Launcher

object Controller {
    val id: String = Id.genId()

    val launchers = arrayListOf<Launcher>()
}
