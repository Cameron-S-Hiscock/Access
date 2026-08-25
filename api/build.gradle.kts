plugins {
    id("conventions")
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":systems"))
}

println("API : CONFIGURATION")
