package io.github.rastiehaiev

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class IrDumpGradleSupportPlugin : KotlinCompilerPluginSupportPlugin {

    override fun applyToCompilation(
        kotlinCompilation: KotlinCompilation<*>,
    ): Provider<List<SubpluginOption>> = with(kotlinCompilation.target) {
        project.provider {
            val extension = project.getIrExtension()
            if (extension.enabled) {
                generateSubPluginOptions(project) ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    private fun generateSubPluginOptions(project: Project) =
        project.getIrOutputDir().get().asFile
            .listFiles()
            .toList()
            .sortedBy { it.name }
            .lastOrNull()
            ?.let { dir ->
                listOf(
                    SubpluginOption("enabled", "true"),
                    SubpluginOption("outputDir", dir.absolutePath),
                )
            }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = with(kotlinCompilation.target.project) {
        plugins.hasPlugin(IrDumpGradleSupportPlugin::class.java) && getIrExtension().enabled
    }

    override fun getCompilerPluginId(): String = with(PluginConfiguration) { "$GROUP_ID.$ARTIFACT_ID_GRADLE" }

    override fun getPluginArtifact() = with(PluginConfiguration) {
        SubpluginArtifact(
            groupId = GROUP_ID,
            artifactId = ARTIFACT_ID_KOTLIN,
            version = VERSION,
        )
    }
}
