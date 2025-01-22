package com.rastiehaiev

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class IrDumpGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create("irDump", IrDumpGradleExtension::class.java)
        target.plugins.apply(IrDumpGradleSupportPlugin::class.java)
    }
}

open class IrDumpGradleExtension(
    var enabled: Boolean = false,
    var outputFileName: String = "ir-dump.txt",
    var append: Boolean = false,
)
