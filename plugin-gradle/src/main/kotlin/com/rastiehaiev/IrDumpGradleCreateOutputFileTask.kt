package com.rastiehaiev

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files

abstract class IrDumpGradleCreateOutputFileTask : DefaultTask() {

    /**
     * Registering this path as task output to prevent its deletion.
     */
    @Suppress("unused")
    @get:OutputDirectory
    val outputDir: Provider<Directory> = project.getIrOutputDir()

    private val outputFile = project.getIrOutputFile()
    private val extension = project.getIrExtension()

    @TaskAction
    fun generateFile() {
        if (extension.enabled) {
            outputFile.also {
                if (!it.exists()) {
                    logger.log("File '${it.absolutePath}' does not exist. Creating...")
                    Files.createFile(it.toPath())
                    logger.log("File '${it.absolutePath}' has been created.")
                }
                logger.log("Using file '${it.absolutePath}'.")
            }
        }
    }
}
