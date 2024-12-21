package com.sinsuren.diff

import org.gradle.api.Action

open class FileSizeComparatorExtension {
    // List to hold multiple file comparisons
    val fileComparisons: MutableList<FileComparisonConfig> = mutableListOf()

    fun fileComparison(action: Action<FileComparisonConfig>) {
        val comparisonConfig = FileComparisonConfig()
        action.execute(comparisonConfig)
        fileComparisons.add(comparisonConfig)
    }
}

open class FileComparisonConfig {
    var inputFilePath1: String? = null
    var inputFilePath2: String? = null
    var outputFilePath: String? = null
}
