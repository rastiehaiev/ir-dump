package com.rastiehaiev

import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class IrDumpGradleSupportPlugin : KotlinCompilerPluginSupportPlugin {

    override fun applyToCompilation(
        kotlinCompilation: KotlinCompilation<*>,
    ): Provider<List<SubpluginOption>> = with(kotlinCompilation.target.project) {
        val extension = extensions.findByType(IrDumpGradleExtension::class.java) ?: IrDumpGradleExtension()
        val outputFileAbsolutePath = resolveOutputFileAbsolutePath(layout, extension.outputFileName)
        provider {
            listOf(
                SubpluginOption("enabled", extension.enabled.toString()),
                SubpluginOption("outputFileAbsolutePath", outputFileAbsolutePath),
                SubpluginOption("append", extension.append.toString()),
            )
        }
    }

    private fun resolveOutputFileAbsolutePath(
        layout: ProjectLayout,
        fileName: String?,
    ): String {
        val outputFileName = fileName ?: IrDumpGradleExtension.DEFAULT_FILE_NAME
        return layout.buildDirectory
            .dir("ir-dump")
            .get()
            .file(outputFileName)
            .asFile
            .absolutePath
    }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = run {
        kotlinCompilation.target.project.plugins.hasPlugin(IrDumpGradleSupportPlugin::class.java)
    }

    override fun getCompilerPluginId(): String = "com.rastiehaiev.ir-dump"

    override fun getPluginArtifact(): SubpluginArtifact {
        return SubpluginArtifact(
            groupId = "com.rastiehaiev",
            artifactId = "ir-dump-plugin-kotlin",
            version = "0.0.1",
        )
    }
}
