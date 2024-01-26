plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.analytics"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Analytics.mixpanel)
}
