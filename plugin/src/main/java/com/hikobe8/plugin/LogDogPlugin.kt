package com.hikobe8.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LogDogPlugin:Plugin<Project> {

    override fun apply(project: Project) {
        println("Hello, LogDog")
    }

}