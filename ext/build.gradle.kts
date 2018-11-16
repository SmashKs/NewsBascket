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
    implementation(Deps.Global.kotlin)
    implementation(Deps.Global.coroutine)

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
