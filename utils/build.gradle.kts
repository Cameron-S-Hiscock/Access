plugins {
    id("conventions")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
}

println("UTILS : CONFIGURATION")
