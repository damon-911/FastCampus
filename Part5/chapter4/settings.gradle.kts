pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "chapter4"
include(":app")
include(":core")
include(":ui_components")
include(":features:feed")
include(":features:detail")
include(":libraries:network")
include(":libraries:network-contract")
include(":libraries:storage")
include(":libraries:storage-contract")
