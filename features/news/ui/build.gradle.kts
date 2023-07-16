plugins {
    id("com.android.library")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.news.ui"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(viewmodelKtx)
        implementation(lifecycleKtx)
    }
    with(Dependencies.Navigation) {
        implementation(navigationUIKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationDynamicFeatureFragment)
    }
    implementation(Dependencies.Glide.glide)
    implementation(Dependencies.Shimmer.shimmer)
    implementation(Dependencies.Lottie.lottie)

    testImplementation("junit:junit:4.13.2")

    with(Modules) {
        implementation(project(mapOf("path" to core)))
        implementation(project(mapOf("path" to coreUi)))
        implementation(project(mapOf("path" to newsDomain)))
    }

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
