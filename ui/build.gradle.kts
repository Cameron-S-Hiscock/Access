plugins {
    id("conventions")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(project(":core"))
    implementation(project(":utils"))
}

println("UI : CONFIGURATION")
