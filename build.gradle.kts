import com.diffplug.gradle.spotless.SpotlessExtension
import com.github.spotbugs.SpotBugsExtension

plugins {
    java
    id("com.diffplug.gradle.spotless")
    id("com.github.spotbugs")
}

allprojects {
    apply(plugin = "idea")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "checkstyle")
    apply(plugin = "com.diffplug.gradle.spotless")
    apply(plugin = "com.github.spotbugs")

    repositories {
        jcenter()
    }

    dependencies {
        implementation("edu.princeton.cs:algs4:1.0.4")

        "checkstyle"("com.puppycrawl.tools:checkstyle:8.24")
        "checkstyle"(rootProject.files("libs/checkstyle-lift.jar"))

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

        register("zipWithCheck") {
            dependsOn("check", "zip")
        }

        named("checkstyleTest").configure {
            enabled = false
        }
    }

    configure<SpotlessExtension> {
        java {
            licenseHeaderFile(rootProject.file("src/mit-license.java"), "(package|import|public class) ")
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    configure<CheckstyleExtension> {
        configFile = rootProject.file("src/checkstyle-coursera.xml")
        configProperties["suppressions"] = rootProject.file("src/checkstyle-suppressions.xml").absolutePath
    }

    configure<SpotBugsExtension> {
        excludeFilter = rootProject.file("src/spotbugs.xml")
    }
}
