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
    val dir = file("src/main/resources")
    onlyIf { !File(dir, "access-local.crt").exists() }
    
    commandLine(
        "openssl", "req", "-x509", "-newkey", "ec",
        "-pkeyopt", "ec_paramgen_curve:prime256v1",
        "-nodes", "-days", "3650",
        "-keyout", File(dir, "access-local.key").absolutePath,
        "-out", File(dir, "access-local.crt").absolutePath,
        "-subj", "/CN=localhost/OU=Access/O=Access/C=US",
        "-addext", "subjectAltName=DNS:localhost,IP:127.0.0.1"
    )
}

tasks.named("processResources") {
    dependsOn("genDevCert")
}

application {
    mainClass.set("com.cameronsh.app.AppKt")
}
