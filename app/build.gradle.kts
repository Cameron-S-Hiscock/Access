import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    id("conventions")
    application
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":api"))
    implementation(project(":systems"))
    implementation(project(":data"))
}

tasks.named<JavaExec>("run") {
    workingDir = rootProject.projectDir
}

tasks.register<Exec>("genDevCert") {
    val keystoreFile = file("src/main/resources/access-local.jks")
    onlyIf { !keystoreFile.exists() }

    commandLine(
        "keytool", "-genkeypair",
        "-alias", "access-local",
        "-keyalg", "EC",
        "-keysize", "256",
        "-validity", "3650",
        "-keystore", keystoreFile.absolutePath,
        "-storepass", "changeit",
        "-dname", "CN=localhost, OU=Access, O=Access, L=NA, ST=NA, C=US"
    )
}

tasks.named("processResources") {
    dependsOn("genDevCert")
}

application {
    mainClass.set("com.cameronsh.app.AppKt")
}
