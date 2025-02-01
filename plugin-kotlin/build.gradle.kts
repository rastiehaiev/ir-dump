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
            artifactId = "ir-dump-compiler-plugin"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
