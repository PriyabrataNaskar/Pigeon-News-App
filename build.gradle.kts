// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(Dependencies.Navigation.safeArgsClassPath)
    }
}
plugins {
    id("com.android.application") version Dependencies.Build.agp apply false
    id("com.android.library") version Dependencies.Build.agp apply false
    id("org.jetbrains.kotlin.android") version Dependencies.Kotlin.kotlin apply false
    id("com.google.dagger.hilt.android") version Dependencies.Hilt.hiltVersion apply false
}
