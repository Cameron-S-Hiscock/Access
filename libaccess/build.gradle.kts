plugins {
    id("conventions")
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":systems"))
    implementation(project(":data"))
}

tasks.register<Exec>("buildLibAccess") {
    description = "Builds libaccess"
    workingDir = file("../libaccess")
    commandLine("cmake", "-B", "./build", "-S", ".", "-G", "Ninja")
    // commandLine("cmake", "--build", "./build")
}

tasks.register<Copy>("copyLibAccess") {
    dependsOn("buildLibAccess")
    val osName = System.getProperty("os.name").lowercase()
    val (platformDir, libName) = when {
        osName.contains("win") -> "windows" to "access.dll"
        osName.contains("mac") -> "macos" to "libaccess.dylib"
        else -> "linux" to "libaccess.so"
    }
    val arch = if(System.getProperty("os.arch").contains("aarch64")) "aarch64" else "x86_64"
    from("../build/$libName")
    into("src/main/resources/native/$platformDir/$arch")
}

tasks.named("processResources") {
    dependsOn("copyLibAccess")
}
