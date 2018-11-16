import dependenices.Deps

plugins {
    id("java-library")
    id("kotlin")
    id("jacoco")
}

apply {
    from("https://raw.githubusercontent.com/pokk/JCenterUploader/master/PureJavaJacoco.gradle.kts")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))
    implementation(project(":ext"))

    implementation(Deps.Global.kotlin)
    implementation(Deps.Global.refelect)
    implementation(Deps.Global.coroutine)

    implementation(Deps.Global.shaver) {
        exclude(group = "io.reactivex.rxjava2", module = "rxjava")
        exclude(group = "io.reactivex.rxjava2", module = "rxkotlin")
    }

    testImplementation(Deps.GlobalTest.kotlin)
    testImplementation(Deps.GlobalTest.mockito) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-reflect")
    }
    testImplementation(Deps.GlobalTest.assertk)

    //region For the the newest version.
    testImplementation(Deps.GlobalTest.byteBuddy)
    testImplementation(Deps.GlobalTest.byteBuddyAgent)
    //endregion
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
