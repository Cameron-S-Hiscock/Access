plugins {
    id("conventions")
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":systems"))
    api("org.jetbrains.exposed:exposed-core:0.55.0")
    api("org.jetbrains.exposed:exposed-dao:0.55.0")
    api("org.jetbrains.exposed:exposed-jdbc:0.55.0")
    implementation("org.xerial:sqlite-jdbc:3.50.2.0")
}