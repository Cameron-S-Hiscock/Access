plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
}

group = "net.accesstechnologies"
version = "unspecified"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.runtime)
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

println("UI : CONFIGURATION")