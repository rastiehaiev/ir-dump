package com.rastiehaiev

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@OptIn(ExperimentalCompilerApi::class)
class IrDumpCommandLineProcessor : CommandLineProcessor {
    override val pluginId = "com.rastiehaiev.ir-dump"

    override val pluginOptions = listOf(
        CliOption(
            optionName = "enabled",
            valueDescription = "<true|false>",
            description = "Specifies whether the 'ir-dump' plugin is enabled.",
            required = true,
            allowMultipleOccurrences = false,
        ),
        CliOption(
            optionName = "outputDir",
            valueDescription = "Absolute path to output file",
            description = "Specifies the absolute path to output file.",
            required = true,
            allowMultipleOccurrences = false,
        ),
    )

    override fun processOption(
        option: AbstractCliOption,
        value: String,
        configuration: CompilerConfiguration,
    ) {
        val configurationKeys = configuration.toKeys()
        when (option.optionName) {
            "enabled" -> configurationKeys.setEnabled(value)
            "outputDir" -> configurationKeys.setOutputFileAbsolutePath(value)
            else -> error("Unexpected config option: ${option.optionName}.")
        }
    }
}
