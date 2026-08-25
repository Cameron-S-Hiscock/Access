plugins {
    id("conventions")
}

dependencies {
    implementation("me.friwi:jcefmaven:122.1.10")
    implementation(project(":core"))
    implementation(project(":systems"))
    implementation(project(":utils"))
}

val webSourceDir = layout.projectDirectory.dir("src/web")
val webDistDir = layout.buildDirectory.dir("resources/main/web-dist")

tasks.register<Copy>("buildWeb") {
    from(webSourceDir)
    into(webDistDir)
}

tasks.named("processResources") {
    dependsOn("buildWeb")
}

tasks.named("build") {
    dependsOn("processResources")
}

println("UI : CONFIGURATION")
