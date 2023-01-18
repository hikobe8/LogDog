package com.hikobe8.plugin

import com.android.build.gradle.AppExtension
import com.hikobe8.plugin.transform.LogDogTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class LogDogPlugin:Plugin<Project> {

    override fun apply(project: Project) {
        println("Hello, LogDog")
        val appExt = project.extensions.getByType(AppExtension::class.java)
        appExt.registerTransform(LogDogTransform())
    }

}