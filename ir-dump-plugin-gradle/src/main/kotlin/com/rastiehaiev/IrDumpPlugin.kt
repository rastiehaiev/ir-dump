package com.rastiehaiev

import org.gradle.api.Project
import org.gradle.api.Plugin

class IrDumpPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'com.rastiehaiev.ir-dump'")
            }
        }
    }
}
