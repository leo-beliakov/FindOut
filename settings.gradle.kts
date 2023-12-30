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

rootProject.name = "FindOut"
include(":app")
include(":design_system")
include(":features:root")
include(":features:home")
include(":features:form")
include(":features:profile")
include(":features:creation")
include(":features:mediapicker")
include(":base:database")
