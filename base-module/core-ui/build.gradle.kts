plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.coreui"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(lifecycleKtx)
        implementation(appCompat)
        implementation(materialDesigning)
    }
}
