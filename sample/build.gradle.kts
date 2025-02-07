plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("io.github.rastiehaiev.ir-dump")
}

repositories {
    mavenLocal()
    gradlePluginPortal()
}

val irDumpPluginVersion = gradle.extra["irDumpPluginVersion"] as String

dependencies {
    implementation("io.github.rastiehaiev:ir-dump-annotations:$irDumpPluginVersion")
}

irDump {
    enabled = true
}
