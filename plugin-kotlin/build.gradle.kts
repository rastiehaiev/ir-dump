import io.github.rastiehaiev.getDeployConfiguration

plugins {
    id("buildlogic.kotlin-common-conventions")
}

dependencies {
    compileOnly(libs.kotlin.compiler.embeddable)
}

ext["ir.dump.artifact.id"] = project.getDeployConfiguration().kotlinArtifactId
ext["ir.dump.artifact.description"] = "A Kotlin plugin for generating IR dump files. " +
        "For more information see README.md: https://github.com/rastiehaiev/ir-dump."

apply(plugin = "buildlogic.kotlin-maven-artifact-conventions")
