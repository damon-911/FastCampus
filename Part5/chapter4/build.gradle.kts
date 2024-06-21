// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
    extra.apply {
        set("lifecycle_version", "2.8.2")
        set("coil_version", "2.6.0")
        set("navigation_version", "2.7.7")
        set("hilt_version", "2.51.1")
        set("timber_version", "5.0.1")
        set("retrofit_version", "2.9.0")
        set("gson_version", "2.10.1")
    }
}