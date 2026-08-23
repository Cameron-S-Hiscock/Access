plugins {
   id("conventions") 
}

dependencies {
    implementation(project(":utils"))
    implementation("com.google.guava:guava:33.2.1-jre")
}

println("CORE : CONFIGURATION")
