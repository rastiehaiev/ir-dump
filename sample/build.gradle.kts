plugins {
    id("buildlogic.kotlin-common-conventions")
    id("com.rastiehaiev.ir-dump") version "0.0.1"
}

repositories {
    mavenLocal()
}

dependencies {
    implementation(project(":lib"))
}

irDump {
    enabled = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    doFirst {
        println("KotlinCompile task: ${this.name}")

        println("Free compiler args: ${compilerOptions.freeCompilerArgs.get()}")
        println("JVM target: ${compilerOptions.jvmTarget.get()}")
        println("Other options: ${compilerOptions.allWarningsAsErrors.get()}")
    }
}
