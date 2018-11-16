// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0-alpha02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${dependenices.Versions.Kotlin.kotlinLib}")

        // NOTE: Do not place your application dependencies here; they belong
        //  in the individual module build.gradle files
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(dependenices.Versions.Plugin.detekt)
    id("com.github.ben-manes.versions").version(dependenices.Versions.Plugin.versionUpdater)
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        toolVersion = dependenices.Versions.Plugin.detekt
        debug = true
        parallel = true
        input = files("src/main/java")
        filters = ".*/resources/.*,.*/build/.*"
        config = files("$rootDir/detekt.yml")

        idea {
            path = "$rootDir/.idea"
            codeStyleScheme = "$rootDir/.idea/idea-code-style.xml"
            inspectionsProfile = "$rootDir/.idea/inspect.xml"
            mask = "*.kt"
        }
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

        // required to find the project's artifacts
        maven(url = "https://www.jitpack.io")
        maven(url = "https://dl.bintray.com/pokk/maven")
        maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI/")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
