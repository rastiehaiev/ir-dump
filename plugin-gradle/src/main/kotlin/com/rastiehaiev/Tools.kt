package com.rastiehaiev

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.logging.Logger
import org.gradle.api.provider.Provider
import java.io.File

fun Project.getIrExtension(): IrDumpGradleExtension =
    extensions.findByType(IrDumpGradleExtension::class.java) ?: IrDumpGradleExtension()

fun Project.getIrOutputDir(): Provider<Directory> = layout.buildDirectory.dir("ir-dump")

fun Project.getIrOutputFile(): File {
    val irExtension = getIrExtension()
    val fileName = irExtension.outputFileName ?: "ir-dump.txt"
    return getIrOutputDir().get().file(fileName).asFile
}

fun Logger.log(msg: String) {
    lifecycle("[${IrDumpGradleSupportPlugin::class.simpleName}] $msg")
}
