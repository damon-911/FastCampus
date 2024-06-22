plugins {
    id("com.android.library")
}

apply("../../base-build.gradle")

android {
    namespace = "fastcampus.part5.chapter4.libraries.network"
}

dependencies {
    implementation(project(path = ":libraries:network-contract"))

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${rootProject.extra["retrofit_version"]}")

    // Gson
    implementation("com.google.code.gson:gson:${rootProject.extra["gson_version"]}")
}