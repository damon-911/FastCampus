plugins {
    id("com.android.application")
}

apply("../base-build.gradle")

android {
    namespace = "fastcampus.part5.chapter4"

    flavorDimensions += "theme"

    productFlavors {
        create("red") {
            isDefault = true
            dimension = "theme"
            applicationId = "fastcampus.part5.chapter4.red"
        }
        create("blue") {
            dimension = "theme"
            applicationId = "fastcampus.part5.chapter4.blue"
        }
    }
}

dependencies {
    implementation(project(path = ":features:detail"))
    implementation(project(path = ":features:feed"))
    implementation(project(path = ":libraries:network"))
    implementation(project(path = ":libraries:network-contract"))
    implementation(project(path = ":libraries:storage"))
    implementation(project(path = ":libraries:storage-contract"))
}