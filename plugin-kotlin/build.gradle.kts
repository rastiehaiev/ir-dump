
import io.github.rastiehaiev.getPluginDetails
import org.gradle.api.plugins.catalog.internal.TomlFileGenerator

plugins {
    id("buildlogic.kotlin-plugin-conventions")
    id("eu.kakde.gradle.sonatype-maven-central-publisher") version "1.0.6"
}

private val pluginDetails = project.getPluginDetails()

dependencies {
    compileOnly(libs.kotlin.compiler.embeddable)
}

val centralPortalUsername = findProperty("centralPortalUsername") as String?
val centralPortalPassword = findProperty("centralPortalPassword") as String?

sonatypeCentralPublishExtension {
    groupId.set(project.group.toString())
    artifactId.set(pluginDetails.kotlinArtifactId)
    version.set(pluginDetails.version)
    componentType.set("java")
    publishingType.set("AUTOMATIC")

    username.set(centralPortalUsername)
    password.set(centralPortalPassword)

    pom {
        name.set(pluginDetails.kotlinArtifactId)
        description.set("A Kotlin plugin for generating IR dump files. For more information see README.md: https://github.com/rastiehaiev/ir-dump.")
        url.set("https://github.com/rastiehaiev/ir-dump")
        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            }
        }
        developers {
            developer {
                id.set("rastiehaiev")
                name.set("Roman Rastiehaiev")
                email.set("roman.rastiehaiev@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/rastiehaiev/ir-dump.git")
            developerConnection.set("scm:git:ssh://git@github.com/rastiehaiev/ir-dump.git")
            url.set("https://github.com/rastiehaiev/ir-dump")
        }
        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/rastiehaiev/ir-dump/issues")
        }
    }
}

tasks.withType<TomlFileGenerator> {
    outputFile = layout.buildDirectory.dir("tmp").get().file("libs.versions.toml")
}
