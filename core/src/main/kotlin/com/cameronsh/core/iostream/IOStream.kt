package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.*

class IOStream(ports: Array<Port>) {
    val id = Id.genId()
    val origin: UUID
    val destination: UUID
    for(port in ports) {
        when(port) {
            try {
                is NativePort -> println("NativePort")
                is AppPort -> println("AppPort")
                is ContainerPort -> println("ContainerPort")
                is PluginPort -> println("PluginPort")
                else -> error("Undefined port type: $port")
            }
        }
    }
}
