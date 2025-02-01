plugins {
    id("buildlogic.kotlin-common-conventions")
    id("maven-publish")
    id("java-gradle-plugin")
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    val greeting by plugins.creating {
        id = "com.rastiehaiev.ir-dump"
        implementationClass = "com.rastiehaiev.IrDumpGradlePlugin"
    }
}

val functionalTestSourceSet: SourceSet = sourceSets.create("functionalTest") {
}

configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
configurations["functionalTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
    useJUnitPlatform()
}

gradlePlugin.testSourceSets.add(functionalTestSourceSet)

tasks.named<Task>("check") {
    dependsOn(functionalTest)
}

publishing {
    repositories {
        mavenLocal()
    }
}
