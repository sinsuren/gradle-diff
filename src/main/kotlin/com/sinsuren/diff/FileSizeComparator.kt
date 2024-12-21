package com.sinsuren.diff

import org.gradle.api.Plugin
import org.gradle.api.Project

class FileSizeComparatorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Create an extension to hold configuration
        val extension = project.extensions.create("fileSizeComparator", FileSizeComparatorExtension::class.java)

        // Register the task and configure it using the extension
        project.tasks.register("compareFileSize", CompareFileSizeTask::class.java) { task ->
            task.inputFilePath1 = extension.inputFilePath1
            task.inputFilePath2 = extension.inputFilePath2
            task.outputFilePath = extension.outputFilePath
        }
    }
}
