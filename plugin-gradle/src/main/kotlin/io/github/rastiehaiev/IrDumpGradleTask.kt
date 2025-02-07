package io.github.rastiehaiev

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

abstract class IrDumpGradleTask : DefaultTask() {
    /**
     * Registering this path as task output to prevent its deletion.
     */
    @get:OutputDirectory
    lateinit var outputDir: File

    private val extension = project.getIrExtension()

    @TaskAction
    fun generateFile() {
        if (extension.enabled) {
            if (!outputDir.exists()) {
                logger.log("Directory '${outputDir.absolutePath}' does not exist. Creating...")
                Files.createFile(outputDir.toPath())
                logger.log("Directory '${outputDir.absolutePath}' has been created.")
            }
        }
    }
}
