plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "com.cameronsh"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.4.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

println("CONVENTION : CONFIGURATION")
