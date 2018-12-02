import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import dependenices.BuildSetting
import dependenices.Deps
import dependenices.Versions

tasks.whenObjectAdded {
    if ("lint".toRegex().containsMatchIn(this.name)) {
        this.enabled = BuildSetting.enableLint
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
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
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
