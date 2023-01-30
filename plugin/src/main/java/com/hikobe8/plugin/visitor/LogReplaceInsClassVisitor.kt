package com.hikobe8.plugin.visitor

import com.hikobe8.plugin.bean.MethodInfo
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.ACC_ABSTRACT
import org.objectweb.asm.Opcodes.ACC_NATIVE

class LogReplaceInsClassVisitor(
    api: Int,
    classWriter: ClassWriter,
    private val oldMethodInfo: MethodInfo,
    private val newMethodInfo: MethodInfo
) : ClassVisitor(api, classWriter) {

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val mv = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (null != mv && ("<init>" == name).not() && ("<clinit>" == name).not()) {
            val abstractMethod = (access.and(ACC_ABSTRACT)) != 0
            val nativeMethod = (access.and(ACC_NATIVE)) != 0
            if (abstractMethod.not() && nativeMethod.not()) {
                return LogReplaceInsMethodVisitor(api, mv, oldMethodInfo, newMethodInfo)
            }
        }
        return mv
    }

}