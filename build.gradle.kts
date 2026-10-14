// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("org.sonarqube") version "5.1.0.4882"
}

sonar {
    properties {
        property("sonar.projectKey", "xhuriken_OSauce")
        property("sonar.projectName", "OSauce")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

