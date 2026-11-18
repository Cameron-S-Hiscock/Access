plugins {
   id("access.core-convention") 
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":systems"))
    implementation("com.google.guava:guava:33.7.1-jre")
}
