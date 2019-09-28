import com.github.spotbugs.SpotBugsTask

plugins {
//    checkstyle
//    findbugs
    id("com.github.spotbugs") version "2.0.0"
//    pmd
}

extra["zipName"] = "kdtree.zip"
extra["zipFiles"] = listOf("PointSET.java", "KdTree.java")

dependencies {
//    checkstyle("edu.princeton.cs:algs4:1.0.4")
    checkstyle("com.puppycrawl.tools:checkstyle:8.24")
    checkstyle(files("../libs/checkstyle-lift.jar"))
}

checkstyle {

//    toolVersion = "8.25"
//    configFile = file("config/checkstyle/google-checks.xml")
    configFile = file("config/checkstyle/checkstyle-coursera.xml")

    configProperties["suppressions"] = file("config/checkstyle/checkstyle-suppressions.xml").absolutePath
//    configProperties["fileExtensions"] = "java"

}
//println(configurations["checkstyle"].files)

//findbugs {
//    excludeFilter(file("sdf"))
//    setExcludeFilter(file("config/spotbugs.xml"))
//}

spotbugs {
    excludeFilter = file("config/spotbugs.xml")

}

tasks.withType<SpotBugsTask>().configureEach {
    reports {
        xml.isEnabled = false;
        html.isEnabled = true
    }
}
