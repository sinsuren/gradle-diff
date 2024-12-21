package com.sinsuren.diff

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

abstract class CompareFileSizeTask : DefaultTask() {

    @Input
    var inputFilePath1: String? = null

    @Input
    var inputFilePath2: String? = null

    @OutputFile
    var outputFilePath: String? = null

    @TaskAction
    @Throws(IOException::class)
    fun compareFileSize() {
        val file1Path: Path = Paths.get(inputFilePath1)
        val file2Path: Path = Paths.get(inputFilePath2)
        val outputPath: Path = Paths.get(outputFilePath)

        // Check file existence
        if (!Files.exists(file1Path)) {
            throw IllegalArgumentException("File 1 does not exist: $inputFilePath1")
        }
        if (!Files.exists(file2Path)) {
            throw IllegalArgumentException("File 2 does not exist: $inputFilePath2")
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
        println("Comparison result written to: $outputFilePath")
    }
}
