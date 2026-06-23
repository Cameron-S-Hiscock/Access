plugins {
    kotlin("jvm")
}

group = "net.accesstechnologies"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

println("SYSTEMS : CONFIGURATION")