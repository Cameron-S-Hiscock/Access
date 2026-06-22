import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    application
}

group = "org.access"
version = "unspecified"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.runtime)
    testImplementation(kotlin("test"))
    implementation(project(":core"))
    implementation(project(":ui"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.access.app.AppKt")
}

println("APP : CONFIGURATION")