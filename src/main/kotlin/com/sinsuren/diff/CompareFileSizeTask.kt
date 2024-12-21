package com.sinsuren.diff

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files
import java.nio.file.Paths

abstract class CompareFileSizeTask : DefaultTask() {

    @Input
    var inputFilePath1: String? = null

    @Input
    var inputFilePath2: String? = null

    @OutputFile
    var outputFilePath: String? = null

    @TaskAction
    fun compareFileSize() {
        // Fetch dynamic properties from the command line
        val filePath1 = project.findProperty("inputFilePath1") as String? ?: inputFilePath1
        val filePath2 = project.findProperty("inputFilePath2") as String? ?: inputFilePath2
        val outPath = project.findProperty("outputFilePath") as String? ?: outputFilePath

        // Validate inputs
        if (filePath1.isNullOrEmpty() || filePath2.isNullOrEmpty() || outPath.isNullOrEmpty()) {
            throw IllegalArgumentException("All file paths must be provided (inputFilePath1, inputFilePath2, outputFilePath).")
        }

        val file1Path = Paths.get(filePath1)
        val file2Path = Paths.get(filePath2)
        val outputPath = Paths.get(outPath)

        // Check file existence
        if (!Files.exists(file1Path)) {
            throw IllegalArgumentException("File 1 does not exist: $filePath1")
        }
        if (!Files.exists(file2Path)) {
            throw IllegalArgumentException("File 2 does not exist: $filePath2")
        }

        // Compare file sizes
        val size1 = Files.size(file1Path)
        val size2 = Files.size(file2Path)
        val result = if (size1 >= size2) {
            "File with larger size: ${file1Path.fileName}"
        } else {
            "File with larger size: ${file2Path.fileName}"
        }

        // Write result to output file
        Files.writeString(outputPath, result)
        println("Comparison result written to: $outPath")
    }
}