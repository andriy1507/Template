plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id(Ktlint.Plugin) version Ktlint.Version
    id(Detekt.Plugin) version Detekt.Version
    id(Jetbrains.Dokka.Plugin) version KotlinVersion
}

android {
    namespace = "com.spaceapps.template"
    compileSdk = CompileSdk

    defaultConfig {
        applicationId = "com.spaceapps.template"
        minSdk = MinSdk
        targetSdk = CompileSdk
        versionCode = VersionCode
        versionName = VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    packagingOptions {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    hilt {
        enableAggregatingTask = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeVersion
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
    //    Project dependencies
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-utils"))

    implementation(AndroidX.Core.Ktx)
    implementation(AndroidX.AppCompat.AppCompat)
    implementation(AndroidX.Activity.Ktx)
    implementation(AndroidX.Activity.Compose)
    //    Kotlin
    implementation(platform(Jetbrains.Kotlin.Bom))
    implementation(Jetbrains.Kotlin.StdLib)
    coreLibraryDesugaring(Android.Tools.Desugar)
    //    Dagger-Hilt
    implementation(Google.Dagger.HiltAndroid)
    kapt(Google.Dagger.HiltAndroidCompiler)
    //    Compose
    implementation(platform(AndroidX.Compose.Bom))
    implementation(AndroidX.Compose.Material3)
    implementation(AndroidX.Compose.Ui)
    implementation(AndroidX.Compose.Foundation)
    implementation(AndroidX.Compose.UiToolingPreview)
    debugImplementation(AndroidX.Compose.UiTooling)
}
