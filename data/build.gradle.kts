import dependenices.Deps
import dependenices.Versions
import java.net.NetworkInterface
import java.util.Properties

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
        val (URL_SERVER, remote) = "URL_SERVER" to "remote_url"
        val (API_REQUEST, remoteDomain) = "API_REQUEST" to "remote_api_domain"
        val (GOOGLE_NEWS_API_KEY, apiKeyOfGoogleNews) = "GOOGLE_NEWS_API_KEY" to "google_new_api_key"

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
            buildConfigField("String", URL_SERVER, getProps(remote))
            buildConfigField("String", API_REQUEST, getProps(remoteDomain))
            buildConfigField("String", GOOGLE_NEWS_API_KEY, getProps(apiKeyOfGoogleNews))
        }
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
//            buildConfigField("String", URL_SERVER, getProps(remote))
            buildConfigField("String", URL_SERVER, "\"http://${getLocalIp("en0")}:55667\"")
            buildConfigField("String", API_REQUEST, getProps(remoteDomain))
            buildConfigField("String", GOOGLE_NEWS_API_KEY, getProps(apiKeyOfGoogleNews))
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
    implementation(Deps.Global.shaver) {
        exclude(group = "io.reactivex.rxjava2")
    }
    implementation(Deps.Global.gson)

    kapt(Deps.Data.roomAnnotation)
    implementation(Deps.Data.room)
    implementation(Deps.Data.mmkv)

    //region Internet & Image loading
    implementation(Deps.Data.retrofit2)
    implementation(Deps.Data.retrofit2AdapterCoroutine) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core")
    }
    //endregion

    implementation(Deps.Data.firebaseDB)
}

apply {
    from("https://raw.githubusercontent.com/pokk/JCenterUploader/master/AndroidJacocoNew.gradle.kts")
}

// Get a variable from local.properties.
fun getProps(propName: String): String {
    val propsFile = rootProject.file("local.properties")

    if (!propsFile.exists()) return "\"No_Exist\""

    val props = Properties()

    props.load(propsFile.inputStream())
    return if (props[propName] != null) props[propName].toString() else "\"There_is_no_parameter\""
}

// Get the ip address by interface name
// en0 is WIFI interface
fun getLocalIp(interfaceName: String) = NetworkInterface.getByName(interfaceName)
    ?.interfaceAddresses
    ?.find { it.address.hostAddress.length <= 15 }
    ?.address
    ?.hostAddress
    .orEmpty()
