/*
 * This source file was generated by the Gradle 'init' task
 */
package com.rastiehaiev

import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Disabled
import kotlin.test.Test
import kotlin.test.assertNotNull

@Disabled
class IrDumpPluginTest {

    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.rastiehaiev.ir-dump")

        // Verify the result
        assertNotNull(project.tasks.findByName("greeting"))
    }
}
