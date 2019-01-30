import dependenices.Deps
import dependenices.Versions

tasks.whenObjectAdded {
    if (name.contains("lint") ||
        name == "clean" ||
        name.contains("lintVitalRelease")) {
        enabled = false
    }
}

plugins {
    id("com.android.library")
    kotlin("android")
    id("jacoco")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)
    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.compileSdk)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
        getByName("debug") {
            splits.abi.isEnable = false
            splits.density.isEnable = false
            aaptOptions.cruncherEnabled = false
            isMinifyEnabled = false
            isTestCoverageEnabled = true
            // Only use this flag on builds you don't proguard or upload to beta-by-crashlytics.
            ext.set("alwaysUpdateBuildId", false)
        }
    }
    lintOptions { isAbortOnError = false }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(project(":ext"))

    implementation(Deps.Global.kotlin)

    implementation(Deps.Widget.appcompat)
    implementation(Deps.Widget.quickDialog)
    implementation(Deps.Widget.constraintLayout)
}

apply {
    from("https://raw.githubusercontent.com/pokk/JCenterUploader/master/AndroidJacocoNew.gradle.kts")
}
