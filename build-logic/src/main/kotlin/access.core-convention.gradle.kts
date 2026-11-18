plugins {
    kotlin("jvm")
}

group = "com.csh_s"
version = "unspecified"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.20")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    implementation("org.graalvm.polyglot:polyglot:24.1.1")
    implementation("org.graalvm.polyglot:js:24.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(25)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

