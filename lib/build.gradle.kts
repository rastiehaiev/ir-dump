import io.github.rastiehaiev.getDeployConfiguration

plugins {
    id("buildlogic.kotlin-common-conventions")
}

val libsArtifactId = project.getDeployConfiguration().libsArtifactId

ext["ir.dump.artifact.id"] = libsArtifactId
ext["ir.dump.artifact.description"] = "A Kotlin lib for generating IR dump files. " +
        "For more information see README.md: https://github.com/rastiehaiev/ir-dump."

apply(plugin = "buildlogic.kotlin-maven-artifact-conventions")
