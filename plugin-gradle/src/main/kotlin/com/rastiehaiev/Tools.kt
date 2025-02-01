package com.rastiehaiev

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.logging.Logger
import org.gradle.api.provider.Provider

fun Project.getIrExtension(): IrDumpGradleExtension =
    extensions.findByType(IrDumpGradleExtension::class.java) ?: IrDumpGradleExtension()

fun Project.getIrOutputDir(): Provider<Directory> = layout.buildDirectory.dir("ir-dump")

fun Logger.log(msg: String) {
    lifecycle("[${IrDumpGradlePlugin::class.simpleName}] $msg")
}
