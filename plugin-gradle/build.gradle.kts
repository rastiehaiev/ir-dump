import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.github.rastiehaiev.getPluginDetails

plugins {
    id("buildlogic.kotlin-plugin-conventions")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
    id("com.gradleup.shadow")
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
    compileOnly(libs.kotlin.gradle.plugin)
    testImplementation(libs.kotlin.gradle.plugin)
}

private val pluginDetails = project.getPluginDetails()

gradlePlugin {
    website = "https://github.com/rastiehaiev/ir-dump"
    vcsUrl = "https://github.com/rastiehaiev/ir-dump.git"
    plugins {
        create("irDump") {
            id = "${pluginDetails.groupId}.${pluginDetails.gradleArtifactId}"
            displayName = "IR Dump Gradle compiler plugin"
            implementationClass = "io.github.rastiehaiev.IrDumpGradlePlugin"
            description = "A Gradle plugin for generating IR dump files. For more information see README.md: https://github.com/rastiehaiev/ir-dump."
            tags.set(listOf("compiler", "kotlin"))
        }
    }
}

publishing {
    publications {
        withType<MavenPublication>().configureEach {
            artifactId = pluginDetails.gradleArtifactId
        }
    }
    repositories {
        mavenLocal()
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}
