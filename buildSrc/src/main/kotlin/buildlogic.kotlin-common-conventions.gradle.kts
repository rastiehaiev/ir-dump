import io.github.rastiehaiev.PluginConfigurationGenerator

plugins {
    id("org.jetbrains.kotlin.jvm")
}

private fun resolveProperty(name: String): String = project.findProperty(name)?.toString()
    ?: error("Please define property '$name'.")

private val pluginGroupId = resolveProperty("ir.dump.plugin.group.id")
private val pluginVersionNumber = resolveProperty("ir.dump.plugin.version")
private val pluginGradleArtifactId = resolveProperty("ir.dump.plugin.gradle.artifact.id")
private val pluginKotlinArtifactId = resolveProperty("ir.dump.plugin.kotlin.artifact.id")

group = pluginGroupId
version = pluginVersionNumber

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

val sourcesDir = File(project.layout.buildDirectory.asFile.get(), "generated-sources/src/main/kotlin/io/github/rastiehaiev")
val pluginConfigurationGeneratorTask = tasks.register<PluginConfigurationGenerator>(
    "generateBuildConfig",
    pluginGroupId,
    pluginVersionNumber,
    pluginGradleArtifactId,
    pluginKotlinArtifactId,
    sourcesDir,
)

tasks.named("compileKotlin") {
    dependsOn("generateBuildConfig")
}

kotlin {
    jvmToolchain(21)
    sourceSets {
        main {
            kotlin.srcDir(pluginConfigurationGeneratorTask.map { it.sourcesDir })
        }
    }
}
