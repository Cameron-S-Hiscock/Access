plugins {
    kotlin("jvm")
}

group = "com.cameronsh"
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

println("CONVENTION : CONFIGURATION")
