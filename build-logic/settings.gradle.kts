pluginManagement {
    plugins {
        kotlin("jvm") version "2.4.10"
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

rootProject.name = "build-logic"

