plugins {
    java
    id("com.diffplug.gradle.spotless")
}

allprojects {
    apply(plugin = "idea")
    apply(plugin = "com.diffplug.gradle.spotless")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "checkstyle")

    repositories {
        jcenter()
    }

    dependencies {
        implementation("edu.princeton.cs:algs4:1.0.4")

        testImplementation("org.assertj:assertj-core:3.13.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.0")
    }

    tasks {
        withType<Test> {
            useJUnitPlatform()
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_1_8.majorVersion
            targetCompatibility = JavaVersion.VERSION_1_8.majorVersion
        }

        compileTestJava {
            sourceCompatibility = JavaVersion.VERSION_12.majorVersion
            targetCompatibility = JavaVersion.VERSION_12.majorVersion
        }

        register<Zip>("zip") {
            from("src/main/java")
            @Suppress("UNCHECKED_CAST")
            include(project.extra["zipFiles"] as List<String>)
            archiveFileName.set(project.extra["zipName"] as String)
        }

        register("zipWithChecks") {
            dependsOn("check", "zip")
        }

        named("checkstyleTest").configure {
            enabled = false
        }
    }

    spotless {
        java {
            licenseHeaderFile(rootProject.file("src/mit-license.java"), "(package|import|open|module)")
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}