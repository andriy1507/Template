plugins {
    id("com.android.library")
    kotlin("android")
    id(Ksp.Plugin)
    id(Ktlint.Plugin) version Ktlint.Version
    id(Detekt.Plugin) version Detekt.Version
    id(Jetbrains.Dokka.Plugin) version Jetbrains.Dokka.Version
}

android {
    namespace = "com.spaceapps.template.core.data"
    compileSdk = CompileSdk

    defaultConfig {
        minSdk = MinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = JvmTarget
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(false)
    enableExperimentalRules.set(false)
    disabledRules.set(setOf("no-wildcard-imports", "max-line-length", "import-ordering"))
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

detekt {
    config = files("$rootDir/.detekt/config.yml")
}

dependencies {
    implementation(AndroidX.Core.Ktx)
    implementation(AndroidX.AppCompat.AppCompat)
    coreLibraryDesugaring(Android.Tools.Desugar)
    //    Project dependencies
    implementation(project(Project.Core.Local))
    implementation(project(Project.Core.Remote))
    //    Dagger-Hilt
    implementation(Google.Dagger.HiltAndroid)
    ksp(Google.Dagger.HiltAndroidCompiler)
}
