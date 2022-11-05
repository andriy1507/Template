dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
rootProject.name = "Template"
include(":app")
include(":core:domain")
include(":core:ui")
include(":core:navigation")
include(":ui:hello")
include(":core:data")
