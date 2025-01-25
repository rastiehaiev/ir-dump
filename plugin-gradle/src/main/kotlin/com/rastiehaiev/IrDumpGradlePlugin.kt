package com.rastiehaiev

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("unused")
class IrDumpGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create("irDump", IrDumpGradleExtension::class.java)
        target.plugins.apply(IrDumpGradleSupportPlugin::class.java)

        val irGenerateOutputFileTask = target.tasks.register(
            "irCreateOutputFile",
            IrDumpGradleCreateOutputFileTask::class.java,
        )
        target.afterEvaluate {
            target.tasks.withType(KotlinCompile::class.java).configureEach { compileTask ->
                compileTask.dependsOn(irGenerateOutputFileTask)
            }
        }
    }
}

open class IrDumpGradleExtension(
    var enabled: Boolean = false,
    var outputFileName: String? = null,
    var append: Boolean = false,
)
