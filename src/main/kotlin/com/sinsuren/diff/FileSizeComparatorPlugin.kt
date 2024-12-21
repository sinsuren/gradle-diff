package com.sinsuren.diff

import groovy.util.logging.Slf4j
import org.gradle.api.Plugin
import org.gradle.api.Project

@Slf4j
class FileSizeComparatorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("fileSizeComparator", FileSizeComparatorExtension::class.java)

        project.afterEvaluate {
            extension.fileComparisons.forEachIndexed { index, config ->
                val taskName = "compareFileSizeTask$index"
//                println("Registering task: $taskName")
//                println("Configuration for task $index -> inputFilePath1: ${config.inputFilePath1}, " +
//                        "inputFilePath2: ${config.inputFilePath2}, outputFilePath: ${config.outputFilePath}")

                project.tasks.register(taskName, CompareFileSizeTask::class.java) { task ->
                    task.group = "File Comparison"
                    task.description = "Compares file sizes for configuration $index."
                    task.inputFilePath1 = config.inputFilePath1
                    task.inputFilePath2 = config.inputFilePath2
                    task.outputFilePath = config.outputFilePath
                }

            }

            // Create an aggregate task that depends on all diff tasks
            project.tasks.create("compareFileSizeTaskAll") { task ->
                task.group = "File Comparison"
                task.description = "Performs all diff tasks"
                task.dependsOn(project.tasks.filter { it.name.startsWith("compareFileSizeTask") && it.name != "compareFileSizeTaskAll" })
            }
        }
    }
}