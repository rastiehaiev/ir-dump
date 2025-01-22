plugins {
    id("buildlogic.kotlin-common-conventions")
    `java-gradle-plugin`
}

gradlePlugin {
    val greeting by plugins.creating {
        id = "com.rastiehaiev.ir-dump"
        implementationClass = "com.rastiehaiev.IrDumpPlugin"
    }
}

val functionalTestSourceSet = sourceSets.create("functionalTest") {
}

configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
configurations["functionalTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
    useJUnitPlatform()
}

// gradlePlugin.testSourceSets.add(functionalTestSourceSet)

/*tasks.named<Task>("check") {
    dependsOn(functionalTest)
}*/
