# File Size Comparator Plugin

The **File Size Comparator Plugin** is a Gradle plugin that compares the sizes of two files and writes the result to a specified output file. This plugin is particularly useful in build pipelines where file size comparisons are necessary.

## Features
- Compares two input files.
- Outputs the name of the larger file (or both if sizes are equal) to a specified output file.
- Configurable through a Gradle extension.

---

## Getting Started

### Applying the Plugin

Add the plugin to your `build.gradle.kts` or `build.gradle` file. If the plugin is published to a Maven repository or used locally, include the following:

#### For Kotlin DSL (`build.gradle.kts`):
```kotlin
plugins {
    id("com.sinsuren.diff.filesizecomparator") version "1.0.0"
}
```

#### For Groovy DSL (`build.gradle`):
```groovy
plugins {
    id 'com.sinsuren.diff.filesizecomparator' version '1.0.0'
}
```

#### For Plugin Repository (`settings.gradle`)
```kotlin

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url "https://nexus.infra.test.net/repository/maven-snapshots"
        }
    }
    plugins {
        ....
    }
}

```

### Configuring the Plugin

Use the `fileSizeComparator` extension to specify the input and output file paths:

#### Kotlin DSL (`build.gradle.kts`):
```kotlin
fileSizeComparator {
    fileComparison {
        inputFilePath1 = "path/to/first/file.txt"
        inputFilePath2 = "path/to/second/file.txt"
        outputFilePath = "path/to/output/result.txt"
    }
}
```

#### Groovy DSL (`build.gradle`):
```groovy
fileSizeComparator {
    fileComparison {
        inputFilePath1 = "path/to/first/file.txt"
        inputFilePath2 = "path/to/second/file.txt"
        outputFilePath = "path/to/output/result.txt"
    }
}
```

---

## Running the Task

Run the `compareFileSize` task to execute the file size comparison:

```bash
./gradlew compareFileSizeAll
./gradlew compareFileSize0
./gradlew compareFileSize1
# counter based on the number of fileComparison blocks

```

The result will be written to the specified `outputFilePath` and displayed in the console.

---

## Example

### Configuration
In `build.gradle.kts`:
```kotlin
fileSizeComparator {
    fileComparison {
        inputFilePath1 = "src/main/resources/file1.txt"
        inputFilePath2 = "src/main/resources/file2.txt"
        outputFilePath = "build/output/comparison_result.txt"
    }
}

```
### Another example with absolute paths:
```kotlin
fileSizeComparator {
    fileComparison {
        inputFilePath1 = "${rootProject.rootDir.absolutePath}/src/main/resources/application.properties"
        inputFilePath2 = "${rootProject.rootDir.absolutePath}/src/main/resources/application-local.properties"
        outputFilePath = "results.txt"
    }

    fileComparison {
    // Another set of comparison.        
    }
}

```

### Output
After running the task, the output file `build/output/comparison_result.txt` will contain a message like:

```
File with larger size: file1.txt
```

---

## Publishing

To publish this plugin to Maven Local:

```bash
./gradlew publishToMavenLocal
```

To publish to Maven Central, configure the necessary credentials and run:

```bash
./gradlew publish
```

---

## License

This plugin is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

---

## Contributing

Contributions are welcome! Feel free to submit issues or pull requests to enhance the plugin.

