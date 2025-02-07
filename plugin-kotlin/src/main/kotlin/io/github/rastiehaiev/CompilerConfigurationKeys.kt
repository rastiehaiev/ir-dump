package io.github.rastiehaiev

import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

private val KEY_ENABLED = CompilerConfigurationKey.create<Boolean>("enabled")
private val KEY_OUTPUT_FILE_ABSOLUTE_PATH = CompilerConfigurationKey.create<String>("outputDir")

class CompilerConfigurationKeys(
    private val config: CompilerConfiguration,
) {

    val enabled: Boolean
        get() = config.get(KEY_ENABLED) == true

    fun setEnabled(value: String) {
        config.put(KEY_ENABLED, value.toBoolean())
    }

    val outputDirAbsolutePath: String
        get() = config.get(KEY_OUTPUT_FILE_ABSOLUTE_PATH)
            ?: error("Missing output file absolute path.")

    fun setOutputFileAbsolutePath(value: String) {
        config.put(KEY_OUTPUT_FILE_ABSOLUTE_PATH, value)
    }
}

fun CompilerConfiguration.toKeys(): CompilerConfigurationKeys = CompilerConfigurationKeys(this)
