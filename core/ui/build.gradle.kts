plugins {
    id("com.android.library")
    kotlin("android")
    id(Ksp.Plugin) version Ksp.Version
    id(Ktlint.Plugin) version Ktlint.Version
    id(Detekt.Plugin) version Detekt.Version
    id(Jetbrains.Dokka.Plugin) version Jetbrains.Dokka.Version
}

android {
    namespace = "com.spaceapps.template.core.ui"
    compileSdk = CompileSdk

    defaultConfig {
        minSdk = MinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    //    Kotlin
    implementation(platform(Jetbrains.Kotlin.Bom))
    implementation(Jetbrains.Kotlin.StdLib)
    coreLibraryDesugaring(Android.Tools.Desugar)
    //    Compose
    implementation(platform(AndroidX.Compose.Bom))
    implementation(AndroidX.Compose.Material3)
    implementation(AndroidX.Compose.Ui)
    implementation(AndroidX.Compose.Foundation)
    implementation(AndroidX.Compose.UiToolingPreview)
    debugImplementation(AndroidX.Compose.UiTooling)
    //    Dagger-Hilt
    implementation(Google.Dagger.HiltAndroid)
    ksp(Google.Dagger.HiltAndroidCompiler)
}
