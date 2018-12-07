package dependenices

/**
 * Collect all libs of the version number.
 */
object Versions {
    /**
     * Gradle android version.
     */
    object Android {
        const val minSdk = 21
        const val targetSdk = 28
        const val buiildTool = "28.0.3"
        const val compileSdk = targetSdk
    }

    /**
     * Related Android component lib version.
     */
    object AndroidComponent {
        const val androidx = "1.0.0"
        const val annotation = "1.0.1"
        const val appCompat = "1.1.0-alpha01"
        const val cardView = androidx
        const val recyclerView = appCompat
        const val constraintLayout = "2.0.0-alpha2"
        const val coordinatorLayout = appCompat
    }

    /**
     * Related Kotlin lib version.
     */
    object Kotlin {
        const val kotlinLib = "1.3.11"
        const val kotlinCoroutine = "1.0.1"
    }

    /**
     * Related view component lib version.
     */
    object ViewComponent {
        const val adaptiveRecyclerView = "1.0.5"
        const val shapeOfView = "1.4.6"
    }

    /**
     * Related Kotlin extensions lib version.
     */
    object KotlinAndroidExt {
        const val dex = "2.0.0"
        const val anko = "0.10.8"
        const val ktx = "1.0.1"
        const val kinfer = "2.1.2"
        const val shaver = "1.1.2"
    }

    object AndroidArchitectureComponent {
        const val aacLifecycle = "2.0.0"
        const val navigation = "1.0.0-alpha07"
        const val room = "2.1.0-alpha01"
    }

    /**
     * Related dependency injection lib version.
     */
    object DI {
        const val kodein = "5.3.0"
    }

    /**
     * Related Firebase lib version.
     */
    object Firebase {
        const val core = "16.0.5"
        const val database = core
        const val auth = "16.0.3"
        const val messaging = "17.3.4"

        const val mlVision = "18.0.2"
        const val mlImageLabel = "17.0.2"
    }

    object TensorFlow {
        const val lite = "0.1.7"
    }

    object CloudStore {
        const val cloudinary = "1.24.1"
    }

    /**
     * Related database lib version.
     */
    object Database {
        const val debug = "1.5.0"
        const val debugDb = "1.0.4"
        const val mmkv = "1.0.14"
    }

    /**
     * Related network lib version.
     */
    object Network {
        const val glide = "4.8.0"
        const val retrofit2 = "2.5.0"
        const val adapterCoroutine = "0.9.2"
        const val okhttp3 = "3.12.0"
        const val okhttpProfiler = "1.0.4"
    }

    /**
     * Related reactive lib version.
     */
    object RxDep {
        const val rxJava2 = "2.2.1"
        const val rxKotlin2 = "2.3.0"
        const val rxPermission2 = "0.9.5@aar"
        const val rxBus = "2.0.0"
    }

    /**
     * Related parser lib version.
     */
    object Parser {
        const val gson = "2.8.5"
        const val jsoup = "1.10.3"
    }

    /**
     * Related Android UI lib version.
     */
    object Ui {
        const val material = "1.1.0-alpha01"
        const val dialog = "1.0.6"
        const val loading = "1.3.0"
        const val materialChip = "1.0.8"
        const val cameraView = "1.5.1"
    }

    /**
     * Related Android unit test lib version.
     */
    object Test {
        const val jUnit = "1.1.0-alpha01"
        const val assertK = "0.12"
        const val runner = "1.1.1-alpha01"
        const val espresso = "3.1.1-alpha01"
        const val kakao = "1.4.0"
        const val powerMockito = "1.7.4"
        const val mockitoKotlin = "2.0.0"
        const val mockitoAndroid = "2.23.0"
        const val mockk = "v1.8.9.kotlin13"
        const val byteBuddy = "1.9.5"

    }

    /**
     * Related extension Plugins lib version.
     */
    object Plugin {
        const val detekt = "1.0.0-RC11"
        const val versionUpdater = "0.20.0"
    }
}
