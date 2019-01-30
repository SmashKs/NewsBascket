import dependenices.Deps
import dependenices.Versions
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import java.util.Properties

tasks.whenObjectAdded {
    if (name == "clean" ||
        name.contains("lint") ||
        name.contains("lintVitalRelease")) {
        enabled = false
    }
}

plugins {
    id("com.android.application")
    id("kotlin-android-extensions")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)
    defaultConfig {
        applicationId = "com.no1.taiwan.newsbasket"
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        renderscriptTargetApi Android.targetSdk
//        renderscriptSupportModeEnabled true  // Enable RS support
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
    }
    packagingOptions {
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/main.kotlin_module")
    }
    sourceSets {
        getByName("main") {
            res.srcDirs(resource.FeatureRes.dirs)
        }
    }
    dexOptions {
        javaMaxHeapSize = "4g"
        jumboMode = true
        preDexLibraries = true
        threadCount = 8
    }
    testOptions { unitTests.isReturnDefaultValues = true }
    lintOptions { isAbortOnError = false }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    mapDiagnosticLocations = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":widget"))
    implementation(project(":ext"))

    //region Kotlin
    implementation(Deps.Global.kotlin)
    implementation(Deps.Global.coroutine)
    implementation(Deps.Global.refelect)
    implementation(Deps.Presentation.androidCoroutine)
    //endregion

    //region Jieyi Libraries
    implementation(Deps.Presentation.knifer)
    implementation(Deps.Presentation.arv)
    implementation(Deps.Presentation.quickDialog)
    //endregion

    //region Anko
    implementation(Deps.Presentation.anko)
    implementation(Deps.Presentation.ankoSdk25)
    implementation(Deps.Presentation.ankoV7)
    implementation(Deps.Presentation.ankoCoroutine)
    implementation(Deps.Presentation.ankoV7Coroutine)
    //endregion

    //region DI
    implementation(Deps.Global.kodeinJVM) {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation(Deps.Global.kodeinCore) {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation(Deps.Global.kodeinAndroidX) {
        exclude(group = "org.jetbrains.kotlin")
    }
    //endregion

    //region Rx Bus
    implementation(Deps.Presentation.rxBus) {
        exclude(group = "com.jakewharton.timber", module = "timber")
//        exclude(group = "io.reactivex.rxjava2", module = "rxandroid")
    }
    //endregion

    //region Internet & Image loading
    implementation(Deps.Presentation.retrofit2)
    implementation(Deps.Presentation.retrofit2Gson)
    implementation(Deps.Presentation.retrofit2Courtine) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core")
    }
    implementation(Deps.Presentation.okhttpLogging)
    implementation(Deps.Presentation.glide)
    implementation(Deps.GlobalDebug.okHttpProfiler)
    //endregion

    //region UI
    implementation(Deps.Presentation.loading)
    //endregion

    //region Firebase & Database
    implementation(Deps.Presentation.firebaseCore)
    implementation(Deps.Presentation.firebaseDB)
    implementation(Deps.Presentation.firebaseVision)
    implementation(Deps.Presentation.firebaseMessaging)
    implementation(Deps.Presentation.firebaseMLImageLabel)
//    implementation (Presentation.cloudinary)
    implementation(Deps.Presentation.room)
    implementation(Deps.Presentation.mmkv)
    //endregion

    //region Android extension
    implementation(Deps.Presentation.ktx)
    implementation(Deps.Presentation.lifecycle)
    //endregion

    //region Androidx SDK
    implementation(Deps.Presentation.dexTool)
    implementation(Deps.Presentation.appcompat)
    implementation(Deps.Presentation.materialDessign)
    implementation(Deps.Presentation.recyclerview)
    implementation(Deps.Presentation.cardview)
    implementation(Deps.Presentation.annot)
    implementation(Deps.Presentation.constraintLayout)
    implementation(Deps.Presentation.coordinatorLayout)
    implementation(Deps.Presentation.navigationUi)
    implementation(Deps.Presentation.navigationFragment)
    implementation(Deps.Presentation.worker)

    implementation(Deps.Presentation.idlingEspresso)
    //endregion

    //region Third-party view components
    implementation(Deps.Presentation.shapeOfView)
    //endregion

    debugImplementation(Deps.GlobalDebug.steho)
    debugImplementation(Deps.GlobalDebug.stehoInterceptor)
    debugImplementation(Deps.GlobalDebug.debugDb)
    debugRuntimeOnly(Deps.GlobalDebug.activityLauncher)

    //region Android Test
    androidTestImplementation(Deps.GlobalTest.junit)
    androidTestImplementation(Deps.GlobalTest.junitCore)
    androidTestImplementation(Deps.GlobalTest.assertk) {
        exclude(group = "org.jetbrains.kotlin")
    }
    androidTestImplementation(Deps.PresentationTest.runner)
    androidTestImplementation(Deps.PresentationTest.espressoCore)
    androidTestImplementation(Deps.PresentationTest.kakao)
    //endregion
}

// Get a variable from local.properties.
fun getProps(propName: String): String {
    val propsFile = rootProject.file("local.properties")

    if (!propsFile.exists()) return "\"No_Exist\""

    val props = Properties()

    props.load(propsFile.inputStream())
    return if (props[propName] != null) props[propName].toString() else "\"There_is_no_parameter\""
}

repositories {
    google()
    mavenCentral()
}

apply(mapOf("plugin" to "com.google.gms.google-services"))
