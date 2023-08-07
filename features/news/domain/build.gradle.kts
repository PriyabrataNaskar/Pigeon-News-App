plugins {
    id("com.android.library")
    id("kotlin-parcelize")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.news.domain"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    with(Modules) {
        implementation(project(mapOf("path" to core)))
    }
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
