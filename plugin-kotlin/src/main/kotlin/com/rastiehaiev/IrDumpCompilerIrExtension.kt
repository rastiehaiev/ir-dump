package com.rastiehaiev

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import java.io.File
import java.nio.file.Files

class IrDumpCompilerIrExtension(
    private val outputDirAbsolutePath: String,
) : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        moduleFragment.files.forEach { file ->
            file.acceptChildrenVoid(IrDumper(outputDirAbsolutePath))
        }
    }
}

private class IrDumper(
    private val outputDirAbsolutePath: String,
) : IrElementVisitorVoid {

    override fun visitDeclaration(declaration: IrDeclarationBase) {
        super.visitDeclaration(declaration)

        // Check if the function is annotated with the desired annotation
        val hasTargetAnnotation = declaration.annotations.any { it.type.render() == "com.rastiehaiev.IrDump" }
        if (hasTargetAnnotation) {
            if (declaration is IrDeclarationParent) {
                log(declaration.kotlinFqName.asString(), declaration.dump())
            }
        }
    }

    private fun log(fqName: String, message: String) {
        File(outputDirAbsolutePath).resolve("$fqName.txt")
            .also { Files.createFile(it.toPath()) }
            .appendText(message)
    }

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }
}
