plugins {
    id("com.android.library")
}

apply("../base-build.gradle")

android {
    namespace = "fastcampus.part5.chapter4.core"
}

dependencies {
    implementation(project(path = ":libraries:network-contract"))
    implementation(project(path = ":libraries:storage-contract"))
    api(project(path = ":ui_components"))

    // Gson
    implementation("com.google.code.gson:gson:${rootProject.extra["gson_version"]}")
}