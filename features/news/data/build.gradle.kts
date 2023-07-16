plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.news.data"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    with(Modules) {
        implementation(project(mapOf("path" to core)))
        implementation(project(mapOf("path" to coreNetwork)))
        implementation(project(mapOf("path" to newsDomain)))
    }
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

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
