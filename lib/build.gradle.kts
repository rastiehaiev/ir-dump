plugins {
    id("buildlogic.kotlin-common-conventions")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("unshaded") {
            artifactId = "ir-dump-annotations"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
