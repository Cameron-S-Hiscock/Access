plugins {
    id("conventions")
}

tasks.register<Exec>("cargoBuild") {
    workingDir = file("$projectDir/src/main/kotlin/com/cameronsh/systems/rs")
    commandLine("cargo", "build", "--release")
}

tasks.register<Copy>("copyNativeLib") {
    dependsOn("cargoBuild")
    val libName = when {
        org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "libsystems.dylib"
        org.gradle.internal.os.OperatingSystem.current().isWindows -> "systems.dll"
        else -> "libsystems.so"
    }
    from("$projectDir/src/main/kotlin/com/cameronsh/systems/rs/target/release/$libName")
    into("$buildDir/resources/main/native")
}

tasks.named("processResources") {
    dependsOn("copyNativeLib")
}

println("SYSTEMS : CONFIGURATION")
