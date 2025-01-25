plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "com.rastiehaiev"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
