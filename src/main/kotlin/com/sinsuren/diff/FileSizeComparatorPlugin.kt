package com.sinsuren.diff

import org.gradle.api.Plugin
import org.gradle.api.Project

class FileSizeComparatorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("fileSizeComparator", FileSizeComparatorExtension::class.java)

        project.afterEvaluate {
            extension.fileComparisons.forEachIndexed { index, config ->
                val taskName = "compareFileSizeTask$index"

                project.tasks.register(taskName, CompareFileSizeTask::class.java) { task ->
                    task.group = "File Comparison"
                    task.description = "Compares file sizes for configuration $index."
                    task.inputFilePath1 = config.inputFilePath1
                    task.inputFilePath2 = config.inputFilePath2
                    task.outputFilePath = config.outputFilePath
                }
            }
        }
    }
}

