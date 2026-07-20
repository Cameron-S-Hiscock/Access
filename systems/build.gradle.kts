plugins {
    id("conventions")
}

tasks.register<Exec>("buildRust") {
    workingDir = file("../systems")
    commandLine("cargo", "build", "--release")
}

tasks.register<Copy>("copyRustLib") {
    dependsOn("buildRust")
    val osName = System.getProperty("os.name").lowercase()
    val (platformDir, libName) = when {
        osName.contains("win") -> "windows" to "systems.dll"
        osName.contains("mac") -> "macos" to "libsystems.dylib"
        else -> "linux" to "libsystems.so"
    }
    val arch = if (System.getProperty("os.arch").contains("aarch64")) "aarch64" else "x86_64"
    from("../systems/target/release/$libName")
    into("src/main/resources/native/$platformDir/$arch")
}

tasks.named("processResources") {
    dependsOn("copyRustLib")
}
println("SYSTEMS : CONFIGURATION")
