package com.hikobe8.plugin.visitor

import com.hikobe8.plugin.bean.MethodInfo
import org.objectweb.asm.MethodVisitor


class LogReplaceInsMethodVisitor(
    api: Int,
    mv: MethodVisitor?,
    private val oldMethodInfo: MethodInfo,
    private val newMethodInfo: MethodInfo
) : MethodVisitor(api, mv) {

    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        if (oldMethodInfo.owner == owner && oldMethodInfo.name == name && oldMethodInfo.desc == descriptor) {
            println("found method ${oldMethodInfo.owner}.${oldMethodInfo.name}, desc = ${oldMethodInfo.desc}")
            super.visitMethodInsn(
                newMethodInfo.opcode,
                newMethodInfo.owner,
                newMethodInfo.name,
                newMethodInfo.desc,
                false
            )
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }
    }

}