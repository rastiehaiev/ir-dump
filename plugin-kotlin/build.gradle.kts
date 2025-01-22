plugins {
    id("buildlogic.kotlin-common-conventions")
    id("maven-publish")
}

dependencies {
    compileOnly(libs.kotlin.compiler.embeddable)
}

publishing {
    publications {
        create<MavenPublication>("unshaded") {
            artifactId = project.name
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
