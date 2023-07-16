plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.corenetwork"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)

    with(Dependencies.Retrofit) {
        implementation(retrofit)
        implementation(gson)
        implementation(gsonConverter)
    }

    with(Dependencies.OkHttp) {
        implementation(platform(okhttpBom))
        implementation(okhttp)
        implementation(loggingInterceptor)
    }
}
