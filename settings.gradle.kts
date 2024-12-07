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
include(":core:navigation")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":core:remote")
include(":core:local")
include(":feature:auth")
include(":feature:settings")
