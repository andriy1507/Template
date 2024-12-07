plugins {
    id("com.android.application")
    kotlin("android")
    id(Ksp.Plugin)
    id(AndroidX.Hilt.Plugin)
    id(Ktlint.Plugin) version Ktlint.Version
    id(Detekt.Plugin) version Detekt.Version
    id(Jetbrains.Dokka.Plugin) version Jetbrains.Dokka.Version
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
    kotlinOptions {
        jvmTarget = JvmTarget
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packaging {
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
    implementation(project(Project.Core.Ui))
    implementation(project(Project.Core.Domain))
    implementation(project(Project.Core.Navigation))
    implementation(project(Project.Core.Remote))
    implementation(project(Project.Feature.Auth))
    implementation(project(Project.Feature.Settings))

    implementation(AndroidX.Core.Ktx)
    implementation(AndroidX.AppCompat.AppCompat)
    implementation(AndroidX.Activity.Ktx)
    implementation(AndroidX.Activity.Compose)
    implementation(AndroidX.Core.SplashScreen)
    implementation(AndroidX.Browser.Browser)
    implementation(AndroidX.Compose.Navigation)
    //    Kotlin
    implementation(platform(Jetbrains.Kotlin.Bom))
    implementation(Jetbrains.Kotlin.StdLib)
    coreLibraryDesugaring(Android.Tools.Desugar)
    //    Dagger-Hilt
    implementation(Google.Dagger.HiltAndroid)
    ksp(Google.Dagger.HiltAndroidCompiler)
    //    Compose
    implementation(platform(AndroidX.Compose.Bom))
    implementation(AndroidX.Compose.Material3)
    implementation(AndroidX.Compose.Ui)
    implementation(AndroidX.Compose.Foundation)
    implementation(AndroidX.Compose.UiToolingPreview)
    debugImplementation(AndroidX.Compose.UiTooling)
    //    Coroutines
    implementation(platform(Jetbrains.KotlinX.Coroutines.Bom))
    implementation(Jetbrains.KotlinX.Coroutines.Core)
    implementation(Jetbrains.KotlinX.Coroutines.Android)
}
