plugins {
    kotlin("jvm") version "1.9.23"
    `java-gradle-plugin`
    `maven-publish`
//    `kotlin-dsl`
//    signing
}

group = "com.sinsuren.diff"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("fileSizeComparatorPlugin") {
//            this id is used in the settings.gradle.kts file
            id = "com.sinsuren.diff.filesizecomparator"
            implementationClass = "com.sinsuren.diff.FileSizeComparatorPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(gradleTestKit())
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

//             Plugin metadata
//            artifactId = "file-size-comparator-plugin"
            groupId = "${project.group}"
            version = "${project.version}"


            pom {
                name.set("File Size Comparator Plugin")
                description.set("A Gradle plugin to compare the sizes of two files.")
                url.set("https://github.com/sinsuren/gradle-diff")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("sinsuren")
                        name.set("Surendra Singh")
                        email.set("xx.xx5@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/sinsuren/gradle-diff.git")
                    developerConnection.set("scm:git:ssh://git@github.com:sinsuren/gradle-diff.git")
                    url.set("https://github.com/sinsuren/gradle-diff")
                }
            }
        }
    }

    repositories {
        // Maven Local
        maven {
            name = "local"
            url = uri("$buildDir/repo")
        }

        // Maven Central (Sonatype)
//        maven {
//            name = "sonatype"
//            url = if (version.toString().endsWith("SNAPSHOT")) {
//                uri("https://oss.sonatype.org/content/repositories/snapshots/")
//            } else {
//                uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
//            }
//
//            credentials {
//                username = findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
//                password = findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
//            }
//        }

        maven {
            name = "internal"
            credentials {
                username = System.getenv("USER")
                password = System.getenv("PASS")
            }
            if (project.version.toString().endsWith("-SNAPSHOT")) {
                url = uri("https://sinsuren.internal.net/repository/maven-snapshots/")
            } else {
                url = uri("https://sinsuren.infra.internal.net/repository/maven-releases/")
            }
        }

    }
}

//signing {
//    useInMemoryPgpKeys(
//        findProperty("signingKey") as String? ?: System.getenv("SIGNING_KEY"),
//        findProperty("signingPassword") as String? ?: System.getenv("SIGNING_PASSWORD")
//    )
//    sign(publishing.publications["mavenJava"])
//}