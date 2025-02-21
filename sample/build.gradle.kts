plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    // id("io.github.rastiehaiev.ir-dump")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
}

val irDumpPluginVersion = gradle.extra["irDumpPluginVersion"] as String
val irDumpPluginGroupId = gradle.extra["irDumpPluginGroupId"] as String
val irDumpPluginLibsArtifactId = gradle.extra["irDumpPluginLibsArtifactId"] as String

/*dependencies {
    implementation("$irDumpPluginGroupId:$irDumpPluginLibsArtifactId:$irDumpPluginVersion")
}

irDump {
    enabled = true
}*/
