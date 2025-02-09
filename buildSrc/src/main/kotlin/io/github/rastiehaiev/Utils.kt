package io.github.rastiehaiev

import org.gradle.api.Project

private fun Project.resolveProperty(name: String): String = findProperty(name)?.toString()
    ?: error("Please define property '$name'.")

fun Project.getPluginDetails(): PluginDetails = PluginDetails(
    groupId = resolveProperty("ir.dump.plugin.group.id"),
    gradleArtifactId = resolveProperty("ir.dump.plugin.gradle.artifact.id"),
    kotlinArtifactId = resolveProperty("ir.dump.plugin.kotlin.artifact.id"),
    libsArtifactId = resolveProperty("ir.dump.plugin.libs.artifact.id"),
    version = resolveProperty("ir.dump.plugin.version"),
)

data class PluginDetails(
    val groupId: String,
    val gradleArtifactId: String,
    val kotlinArtifactId: String,
    val libsArtifactId: String,
    val version: String,
)
