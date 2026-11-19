pluginManagement {
    includeBuild("build-logic")
    plugins {
        kotlin("jvm") version "2.4.20"
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

rootProject.name = "Access"

include("app")

include("api")

include("ui")

include("core")
include("systems")

include("utils")
include("libaccess")
