package io.github.rastiehaiev

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Files
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("unused")
class IrDumpGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val newOutputDirName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
        val outputDirAsFile = target.layout.buildDirectory.dir("ir-dump").get().dir(newOutputDirName).asFile
        Files.createDirectories(outputDirAsFile.toPath())
        target.logger.log("Created new output dir: '${outputDirAsFile.absolutePath}'.")

        target.extensions.create("irDump", IrDumpGradleExtension::class.java)
        target.plugins.apply(IrDumpGradleSupportPlugin::class.java)

        val irGenerateOutputDirTask = target.tasks.register("irCreateOutputDir", IrDumpGradleTask::class.java) {
            it.group = "ir-dump"
            it.outputDir = outputDirAsFile
        }

        target.afterEvaluate {
            target.tasks.withType(KotlinCompile::class.java).configureEach { compileTask ->
                compileTask.dependsOn(irGenerateOutputDirTask)
            }
        }
    }
}

open class IrDumpGradleExtension(var enabled: Boolean = false)
