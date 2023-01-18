package com.hikobe8.plugin.bean

class MethodInfo {
    /**
     * 调用方法的指令码
     */
    val opcode: Int

    /**
     * 方法所属的类名
     */
    val owner: String

    /**
     * 方法名
     */
    val name: String

    /**
     * 方法描述符
     */
    val desc: String

    /**
     * 是否为接口方法
     */
    val isInterfaceMethod: Boolean

    constructor(
        owner: String,
        name: String,
        desc: String
    ) : this(0, owner, name, desc, false)

    constructor(
        opcode: Int,
        owner: String,
        name: String,
        desc: String,
        isInterfaceMethod: Boolean
    ) {
        this.opcode = opcode
        this.owner = owner
        this.name = name
        this.desc = desc
        this.isInterfaceMethod = isInterfaceMethod
    }
}