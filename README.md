# ir-dump

Use this plugin to dump IR into file during compilation.

## Usage

`build.gradle.kts`:
```kotlin
plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("io.github.rastiehaiev.ir-dump") version "0.0.1"
}

repositories {
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    implementation("io.github.rastiehaiev:ir-dump-annotations:0.0.1")
}

irDump {
    enabled = true
}
```

Annotate your classes and/or methods with `@IrDump` annotation. 

`Main.kt`:

```kotlin

package io.github.rastiehaiev

class Example {

    @IrDump
    fun doSomething() {}
}

@IrDump
object AnotherExample
```

Compile your code:
```shell

./gradlew assemble
```

The results are available in `$buildDirectory/ir-dump` directory. Enjoy!
