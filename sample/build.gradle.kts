plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("com.rastiehaiev.ir-dump") version "0.0.1"
}

repositories {
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    implementation("com.rastiehaiev:ir-dump-annotations:0.0.1")
}

irDump {
    enabled = true
}
