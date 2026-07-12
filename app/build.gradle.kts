import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    id("conventions")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    application
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.runtime)
    implementation(project(":utils"))
    implementation(project(":core"))
    implementation(project(":ui"))
}

application {
    mainClass.set("com.cameronsh.app.AppKt")
}

println("APP : CONFIGURATION")
