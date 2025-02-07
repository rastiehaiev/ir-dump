import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("buildlogic.kotlin-common-conventions")
    id("maven-publish")
    id("java-gradle-plugin")
    id("signing")
    alias(libs.plugins.gradle.plugin.publish)
    alias(libs.plugins.gradle.plugin.shadow.jar)
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
    compileOnly(libs.kotlin.gradle.plugin)
    testImplementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    website = "https://github.com/rastiehaiev/ir-dump"
    vcsUrl = "https://github.com/rastiehaiev/ir-dump.git"
    plugins {
        create("irDump") {
            id = "io.github.rastiehaiev.ir-dump"
            displayName = "IR Dump Kotlin compiler plugin"
            implementationClass = "io.github.rastiehaiev.IrDumpGradlePlugin. For more information see README.md: https://github.com/rastiehaiev/ir-dump."
            description = "A Gradle plugin for generating IR dump files"
            tags.set(listOf("compiler", "kotlin"))
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}
