package com.rastiehaiev

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

@Disabled
class IrDumpPluginFunctionalTest {

    @field:TempDir
    lateinit var projectDir: File

    private val buildFile by lazy { projectDir.resolve("build.gradle") }
    private val settingsFile by lazy { projectDir.resolve("settings.gradle") }

    @Test
    fun `should run gradle task`() {
        // Set up the test build
        settingsFile.writeText("")
        buildFile.writeText(
            """
            plugins {
                id('com.rastiehaiev.ir-dump')
            }
        """.trimIndent()
        )

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("greeting")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        assertTrue(result.output.contains("Hello from plugin 'com.rastiehaiev.ir-dump'"))
    }
}
