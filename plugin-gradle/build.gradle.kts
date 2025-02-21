import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.github.rastiehaiev.getDeployConfiguration

plugins {
    id("buildlogic.kotlin-common-conventions")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
    id("maven-publish")
    id("com.gradleup.shadow")
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
    compileOnly(libs.kotlin.gradle.plugin)
    testImplementation(libs.kotlin.gradle.plugin)
}

private val deployConfiguration = project.getDeployConfiguration()

gradlePlugin {
    website = "https://github.com/rastiehaiev/ir-dump"
    vcsUrl = "https://github.com/rastiehaiev/ir-dump.git"
    plugins {
        create("irDump") {
            id = "${deployConfiguration.groupId}.${deployConfiguration.gradleArtifactId}"
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
            artifactId = deployConfiguration.gradleArtifactId
        }
    }
    repositories {
        mavenLocal()
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}
