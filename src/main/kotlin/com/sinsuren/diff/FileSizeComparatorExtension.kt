package com.sinsuren.diff

open class FileSizeComparatorExtension {
    // List to hold multiple file comparisons
    val fileComparisons: MutableList<FileComparisonConfig> = mutableListOf()

    // DSL method to add a file comparison configuration
    fun fileComparison(action: FileComparisonConfig.() -> Unit) {
        val comparisonConfig = FileComparisonConfig()
        comparisonConfig.action()
        fileComparisons.add(comparisonConfig)
    }
}

open class FileComparisonConfig {
    var inputFilePath1: String? = null
    var inputFilePath2: String? = null
    var outputFilePath: String? = null
}
