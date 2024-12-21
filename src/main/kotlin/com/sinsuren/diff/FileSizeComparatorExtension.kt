package com.sinsuren.diff

open class FileSizeComparatorExtension {
    // List of file path configurations
    var fileComparisons: MutableList<FileComparisonConfig> = mutableListOf()

    fun fileComparison(configure: FileComparisonConfig.() -> Unit) {
        val comparison = FileComparisonConfig()
        comparison.configure()
        fileComparisons.add(comparison)
    }
}

open class FileComparisonConfig {
    var inputFilePath1: String? = null
    var inputFilePath2: String? = null
    var outputFilePath: String? = null
}
