object Dependencies {

    object Android {
        private const val coreKtxVersion = "1.10.1"
        private const val lifecycleVersion = "2.3.1"
        private const val activityComposeVersion = "1.5.1"
        private const val materialDesigningVersion = "1.9.0"
        private const val constraintLayoutVersion = "2.1.4"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
        const val materialDesigning = "com.google.android.material:material:$materialDesigningVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val gsonVersion = retrofitVersion

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.google.code.gson:gson:$gsonVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    }

    object OkHttp {
        private const val okhttpBomVersion = "4.10.0"

        val okhttpBom = "com.squareup.okhttp3:okhttp-bom:$okhttpBomVersion"
        val okhttp = "com.squareup.okhttp3:okhttp"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }
	
    object Shimmer {
        private const val shimmerVersion = "0.5.0"
        const val shimmer = "com.facebook.shimmer:shimmer:$shimmerVersion"
    }

    object Lottie {
        private const val lottieVersion = "6.0.0"
        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
    }
	
    object Navigation {
        private const val navigationVersion = "2.5.3"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val navigationDynamicFeatureFragment = "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"
        const val safeArgsClassPath = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object Hilt {
        const val hiltVersion = "2.44"

        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    }

    object Glide {
        private const val glideVersion = "4.13.0"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }

    object Build {
        const val agp = "8.0.1"
    }

    object Kotlin {
        const val kotlin = "1.8.0"
    }
}
