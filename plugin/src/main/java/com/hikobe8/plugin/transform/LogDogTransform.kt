package com.hikobe8.plugin.transform

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.hikobe8.plugin.bean.MethodInfo
import com.hikobe8.plugin.visitor.LogReplaceInsClassVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.File
import java.io.FileOutputStream

class LogDogTransform : Transform() {

    override fun getName() = "LogDogTransform"

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> =
        TransformManager.CONTENT_CLASS


    override fun isIncremental() = false

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> =
        TransformManager.SCOPE_FULL_PROJECT

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        transformInvocation?.inputs?.apply {
            forEach { input ->
                input.directoryInputs.forEach { dirInput ->
                    // java源码
                    dirInput.process(transformInvocation.outputProvider)
                }

                input.jarInputs.forEach { jarInput ->
                    //jar
                    jarInput.process(transformInvocation.outputProvider)
                }
            }
        }
    }


}

private fun DirectoryInput.process(outputProvider: TransformOutputProvider) {
    val dest = outputProvider.getContentLocation(
        name,
        contentTypes,
        scopes,
        Format.DIRECTORY
    )

    //process directory classes
    replaceLogMethod(file)
    FileUtils.mkdirs(dest)
    FileUtils.copyDirectory(file, dest)
}

private fun JarInput.process(outputProvider: TransformOutputProvider) {
    val dest = outputProvider.getContentLocation(
        file.absolutePath,
        contentTypes,
        scopes,
        Format.JAR
    )

    //process jar classes
//    replaceLogMethod(file)
    FileUtils.copyFile(file, dest)
}

private fun replaceLogMethod(file: File) {
    if (!file.isDirectory) {
        println(file.name)
        if (file.name.endsWith(".class") && !file.name.endsWith("LogDog.class")) {
            val oldMethodInfo =
                MethodInfo("android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I")
            val newMethodInfo = MethodInfo(
                Opcodes.INVOKESTATIC,
                "com/hikobe8/logdog/lib/LogDog",
                "i",
                "(Ljava/lang/String;Ljava/lang/String;)I",
                false
            )
            val classReader = ClassReader(file.readBytes())
            val classWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES)
            val classVisitor: ClassVisitor =
                LogReplaceInsClassVisitor(Opcodes.ASM4, classWriter, oldMethodInfo, newMethodInfo)
            classReader.accept(
                classVisitor,
//                ClassReader.SKIP_DEBUG or ClassReader.SKIP_FRAMES
                ClassReader.EXPAND_FRAMES
            )
            FileOutputStream(file).apply {
                write(classWriter.toByteArray())
                close()
            }
        }
    } else {
        file.listFiles()?.apply {
            forEach { replaceLogMethod(it) }
        }
    }

}