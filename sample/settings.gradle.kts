import java.util.*

val properties = Properties().apply {
    file("../gradle.properties").inputStream().use { load(it) }
}

private fun resolveProperty(name: String): String = properties.getProperty(name)?.toString()
    ?: error("Please define property '$name'.")

val pluginGroupId = resolveProperty("ir.dump.plugin.group.id")
val pluginArtifactId = resolveProperty("ir.dump.plugin.gradle.artifact.id")
val pluginVersion = resolveProperty("ir.dump.plugin.version")

val pluginId = "$pluginGroupId.$pluginArtifactId"

gradle.extra.set("irDumpPluginId", pluginId)
gradle.extra.set("irDumpPluginVersion", pluginVersion)

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == gradle.extra.get("irDumpPluginId")) {
                println("Resolved plugin: ${gradle.extra.get("irDumpPluginId")}:${gradle.extra.get("irDumpPluginVersion")}.")
                useVersion(gradle.extra.get("irDumpPluginVersion") as String)
            }
        }
    }
}

rootProject.name = "sample"
