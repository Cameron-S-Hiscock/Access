plugins {
    kotlin("jvm")
}

group = "net.accesstechnologies"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":utils"))
    implementation(project(":ui"))
    implementation(project(":systems"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

println("CORE : CONFIGURATION")