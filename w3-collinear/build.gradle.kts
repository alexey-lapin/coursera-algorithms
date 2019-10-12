plugins {
    id("com.github.johnrengelman.shadow")
    id("me.champeau.gradle.jmh")
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:1.21") {
        exclude(module = "jopt-simple")
    }
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.21")
}

jmh {
    jmhVersion = "1.21"
    duplicateClassesStrategy = DuplicatesStrategy.WARN
    fork = 0
}

tasks {
    named("checkstyleJmh").configure {
        enabled = false
    }
}