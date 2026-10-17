plugins {
    id("conventions")
}

dependencies {
    implementation(project(":utils"))
}

tasks.register<Exec>("buildLibAccess") {
    description = "Builds libaccess"
    workingDir = file("../libaccess")
}

tasks.register<Copy>("copyLibAccess") {
    dependsOn("buildLibAccess")
    val osName = System.getProperty("os.name").lowercase()
    val (platformDir, libName) = when {
        osName.contains("win") -> "windows" to "access.dll"
        osName.contains("mac") -> "macos" to "libaccess.dylib"
        else -> linux to "libaccess.so"
    }
    val arch = if(System.getProperty("os.arch").contains("aarch64")) "aarch64" else "x86_64"
    from("../libaccess/target/release/$libName")
    into("src/main/resources/native/$platformDir/$arch")
}

tasks.named("processResources") {
    dependsOn("copyLibAccess")
}