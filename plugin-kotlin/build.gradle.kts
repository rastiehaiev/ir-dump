import io.github.rastiehaiev.getPluginDetails

plugins {
    id("buildlogic.kotlin-plugin-conventions")
}

dependencies {
    compileOnly(libs.kotlin.compiler.embeddable)
}

private val pluginDetails = project.getPluginDetails()

publishing {
    publications {
        create<MavenPublication>("compilerPlugin") {
            from(components["java"])
            artifactId = pluginDetails.kotlinArtifactId

            pom {
                name.set("IR Dump Kotlin compiler plugin")
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
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("ossrhUsername") as String?
                password = findProperty("ossrhPassword") as String?
            }
        }
    }
}
