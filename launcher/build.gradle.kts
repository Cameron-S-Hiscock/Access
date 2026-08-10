import org.gradle.internal.os.OperatingSystem

val os = OperatingSystem.current()
val libExt = if(os.isWindows) "dll" else "so"
val jniPlatformDir = if(os.isWindows) "win32" else "linux"
val outBinary = if(os.isWindows) "access-launcher.exe" else "access-launcher"

val javaHome = System.getenv("JAVA_HOME") ?: System.getProperty("java.home")
val systemsTarget = project(":systems").projectDir.resolve("target/release")
val appJarTask = project(":app").tasks.named("jar")
val launcherSrc = file("src/start.c")
val launcherOut = layout.buildDirectory.file("native/access-launcher").get().asFile

tasks.register<Exec>("compileLauncher") {
    description = "Compiles C launcher with Clang into access-launcher native binary"

    dependsOn(":systems:buildRust", appJarTask)
    inputs.file(launcherSrc)
    inputs.dir(systemsTarget)
    outputs.file(launcherOut)
    doFirst { launcherOut.parentFile.mkdirs() }

    commandLine(
        "clang", launcherSrc.absolutePath, "-o", launcherOut.absolutePath,
        "-I$javaHome/include", "-I$javaHome/include/$jniPlatformDir",
        *(if(!os.isWindows) arrayOf(
            "-L$javaHome/lib/server", "-ljvm",
            "-L${systemsTarget.absolutePath}", "-lsystems",
            "-Wl,-rpath,$javaHome/lib/server",
            "-Wl,-rpath,${systemsTarget.absolutePath}",
        ) else arrayOf()),
        "-DACCESS_JAR_PATH=\"${appJarTask.get().outputs.files.singleFile.absolutePath}\""
    )
}

tasks.register<Exec>("run") {
    description = "Runs Access with the C launcher"

    dependsOn("compileLauncher")
    commandLine(launcherOut.absolutePath)
}

tasks.register("build") {
    dependsOn("compileLauncher")
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory.dir("native"))
}
