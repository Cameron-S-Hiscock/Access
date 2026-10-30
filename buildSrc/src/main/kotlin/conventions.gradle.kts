plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "com.cameronsh"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.20")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    implementation("org.graalvm.polyglot:polyglot:24.1.1")
    implementation("org.graalvm.polyglot:js:24.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}
