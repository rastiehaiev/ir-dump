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
