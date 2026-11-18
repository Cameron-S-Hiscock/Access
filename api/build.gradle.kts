plugins {
    id("access.core-convention")
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":systems"))
    implementation(project(":libaccess"))
}
