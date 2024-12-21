package com.sinsuren.diff

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class FileSizeComparatorPluginTest {

    @Test
    fun `plugin applies correctly`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("com.sinsuren.diff.filesizecomparator")

        assertNotNull(project.extensions.findByName("fileSizeComparator"))
        assertNotNull(project.tasks.findByName("compareFileSize"))
    }
}
