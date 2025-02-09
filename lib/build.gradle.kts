import io.github.rastiehaiev.getPluginDetails

plugins {
    id("buildlogic.kotlin-common-conventions")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("unshaded") {
            artifactId = project.getPluginDetails().libsArtifactId
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
