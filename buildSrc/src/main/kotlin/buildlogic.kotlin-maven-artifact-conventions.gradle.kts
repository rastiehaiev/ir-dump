import io.github.rastiehaiev.getDeployConfiguration
import org.gradle.api.plugins.catalog.internal.TomlFileGenerator

plugins {
    id("buildlogic.kotlin-common-conventions")
    id("eu.kakde.gradle.sonatype-maven-central-publisher")
}

private val deployConfiguration = project.getDeployConfiguration()

val centralPortalUsername = findProperty("centralPortalUsername") as String?
val centralPortalPassword = findProperty("centralPortalPassword") as String?

sonatypeCentralPublishExtension {
    val artifactIdString = project.findProperty("ir.dump.artifact.id") as String?
    val descriptionString = project.findProperty("ir.dump.artifact.description") as String?

    groupId.set(project.group.toString())
    artifactId.set(artifactIdString)
    version.set(deployConfiguration.version)
    componentType.set("java")
    publishingType.set("AUTOMATIC")

    username.set(centralPortalUsername)
    password.set(centralPortalPassword)

    pom {
        name.set(artifactIdString)
        description.set(descriptionString)
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
