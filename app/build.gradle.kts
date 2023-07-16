plugins {
    id("com.android.application")
}

apply {
    from("$rootDir/common.gradle")
}

android {
    namespace = "com.priyo.pigeon"

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    with(Modules) {
        implementation(project(mapOf("path" to core)))
        implementation(project(mapOf("path" to coreUi)))
        implementation(project(mapOf("path" to coreNetwork)))
        implementation(project(mapOf("path" to newsData)))
        implementation(project(mapOf("path" to newsDomain)))
        implementation(project(mapOf("path" to newsUi)))
    }

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
