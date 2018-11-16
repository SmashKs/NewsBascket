import dependenices.Deps
import dependenices.Versions
import java.net.NetworkInterface

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
            buildConfigField("String", "URL_SERVER", "\"http://${getLocalIp("en0")}:3000\"")
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
    implementation(project(":domain"))
    implementation(project(":ext"))

    implementation(Deps.Global.kotlin)
    implementation(Deps.Global.coroutine)
    implementation(Deps.Global.shaver)
    implementation(Deps.Global.gson)

    kapt(Deps.Data.roomAnnotation)
    implementation(Deps.Data.room)

    //region Internet & Image loading
    implementation(Deps.Data.retrofit2)
    implementation(Deps.Data.retrofit2AdapterCoroutine) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core")
    }
    //endregion
}

apply {
    from("https://raw.githubusercontent.com/pokk/JCenterUploader/master/AndroidJacocoNew.gradle.kts")
}

// Get the ip address by interface name
// en0 is WIFI interface
fun getLocalIp(interfaceName: String) = NetworkInterface.getByName(interfaceName)
    ?.interfaceAddresses
    ?.find { it.address.hostAddress.length <= 15 }
    ?.address
    ?.hostAddress
    .orEmpty()
