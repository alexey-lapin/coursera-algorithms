pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.50"
        id("com.diffplug.gradle.spotless") version "3.24.3"
        id("com.github.spotbugs") version "2.0.0"
        id("me.champeau.gradle.jmh") version "0.4.8"
    }
}

rootProject.name = "coursera-algs4-part1"

include("w1-percolation")
include("w2-queues")
include("w3-collinear-points")
include("w4-sliding-puzzle")
include("w5-kd-tree")
