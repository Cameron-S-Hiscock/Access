plugins {
    kotlin("jvm") version "2.3.21"
}

group = "org.access"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

println("CORE : CONFIGURATION")