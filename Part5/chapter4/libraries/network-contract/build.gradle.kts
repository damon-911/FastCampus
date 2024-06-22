plugins {
    id("com.android.library")
}

apply("../../base-build.gradle")

android {
    namespace = "fastcampus.part5.chapter4.libraries.network_contract"
}

dependencies {
    // Gson
    implementation("com.google.code.gson:gson:${rootProject.extra["gson_version"]}")
}