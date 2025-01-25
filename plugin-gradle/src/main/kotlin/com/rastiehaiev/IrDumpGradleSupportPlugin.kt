package com.rastiehaiev

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
                val outputFileAbsolutePath = project.getIrOutputFile().absolutePath
                listOf(
                    SubpluginOption("enabled", extension.enabled.toString()),
                    SubpluginOption("outputFileAbsolutePath", outputFileAbsolutePath),
                    SubpluginOption("append", extension.append.toString()),
                )
            } else {
                emptyList()
            }
        }
    }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = with(kotlinCompilation.target.project) {
        plugins.hasPlugin(IrDumpGradleSupportPlugin::class.java) && getIrExtension().enabled
    }

    override fun getCompilerPluginId(): String = "com.rastiehaiev.ir-dump"

    override fun getPluginArtifact(): SubpluginArtifact {
        return SubpluginArtifact(
            groupId = "com.rastiehaiev",
            artifactId = "plugin-kotlin",
            version = "0.0.1",
        )
    }
}
