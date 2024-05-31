const val KotlinVersion = "1.9.23"
const val ComposeVersion = "1.5.13"

object Project {

    object Feature {
        private const val Name = ":feature"
        const val Auth = "$Name:auth"
    }
    object Core {
        private const val Name = ":core"
        const val Data = "$Name:data"
        const val Domain = "$Name:domain"
        const val Local = "$Name:local"
        const val Navigation = "$Name:navigation"
        const val Remote = "$Name:remote"
        const val Ui = "$Name:ui"
    }
}

object AndroidX {
    object Browser {
        const val Browser = "androidx.browser:browser:1.8.0"
    }
    object Hilt {
        private const val HiltVersion = "1.2.0"
        const val ViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        const val NavigationCompose = "androidx.hilt:hilt-navigation-compose:$HiltVersion"
        const val Compiler = "androidx.hilt:hilt-compiler:$HiltVersion"
        const val Plugin = "dagger.hilt.android.plugin"
    }

    object Compose {
        const val Bom = "androidx.compose:compose-bom:2024.05.00"
        const val UiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val UiTooling = "androidx.compose.ui:ui-tooling"
        const val UiUtil = "androidx.compose.ui:ui-util"
        const val Ui = "androidx.compose.ui:ui:"
        const val Compiler = "androidx.compose.compiler:compiler"
        const val LiveData = "androidx.compose.runtime:runtime-livedata"
        const val Runtime = "androidx.compose.runtime:runtime"
        const val Material = "androidx.compose.material:material"
        const val Material3 = "androidx.compose.material3:material3"
        const val Icons = "androidx.compose.material:material-icons-extended"
        const val Foundation = "androidx.compose.foundation:foundation"
        const val Animation = "androidx.compose.animation:animation"
        const val Navigation = "androidx.navigation:navigation-compose:2.7.7"
    }

    object Navigation {
        const val Compose = "androidx.navigation:navigation-compose:2.4.2"
    }

    object Core {
        const val Ktx = "androidx.core:core-ktx:1.8.0"
        const val SplashScreen = "androidx.core:core-splashscreen:1.0.0-rc01"
    }

    object AppCompat {
        const val AppCompat = "androidx.appcompat:appcompat:1.6.1"
    }

    object Activity {
        private const val ActivityVersion = "1.9.0"
        const val Ktx = "androidx.activity:activity-ktx:$ActivityVersion"
        const val Compose = "androidx.activity:activity-compose:$ActivityVersion"
    }
    object Lifecycle {
        private const val LifecycleVersion = "2.4.1"
        const val ViewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:$LifecycleVersion"
        const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$LifecycleVersion"
        const val Runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$LifecycleVersion"
    }
    object DataStore {
        private const val Version = "1.1.1"
        const val Preferences = "androidx.datastore:datastore-preferences:$Version"
    }

    object Room {
        private const val Version = "2.6.1"
        const val Runtime = "androidx.room:room-runtime:$Version"
        const val Ktx = "androidx.room:room-ktx:$Version"
        const val Compiler = "androidx.room:room-compiler:$Version"
    }
}

object Android {
    object Tools {
        const val Desugar = "com.android.tools:desugar_jdk_libs:1.1.5"
        const val GradlePlugin = "com.android.tools.build:gradle:8.4.1"
    }
}

object Google {
    object Dagger {
        private const val DaggerVersion = "2.48"
        const val HiltAndroid = "com.google.dagger:hilt-android:$DaggerVersion"
        const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$DaggerVersion"
        const val GradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$DaggerVersion"
    }
}

object Jetbrains {
    object Dokka {
        const val Version = "1.9.20"
        const val Plugin = "org.jetbrains.dokka"
    }

    object Kover {
        const val Version = "0.7.6"
        const val Plugin = "org.jetbrains.kotlinx.kover"
    }

    object Kotlin {
        const val GradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KotlinVersion"
        const val Bom = "org.jetbrains.kotlin:kotlin-bom:$KotlinVersion"
        const val StdLib = "org.jetbrains.kotlin:kotlin-stdlib"
    }

    object KotlinX {
        object Coroutines {
            private const val CoroutinesVersion = "1.6.2"
            const val Bom = "org.jetbrains.kotlinx:kotlinx-coroutines-bom:$CoroutinesVersion"
            const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
            const val Android = "org.jetbrains.kotlinx:kotlinx-coroutines-android"
        }
        object Serialization {
            const val Plugin = "org.jetbrains.kotlin:kotlin-serialization:$KotlinVersion"
            const val Json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3"
        }
    }
}

object Ktlint {
    const val Version = "12.1.0"
    const val Plugin = "org.jlleitschuh.gradle.ktlint"
}

object Detekt {
    const val Version = "1.23.6"
    const val Plugin = "io.gitlab.arturbosch.detekt"
}

object Ksp {
    const val Version = "1.9.23-1.0.19"
    const val Plugin = "com.google.devtools.ksp"
}

object Ktor {
    private const val Version = "2.3.11"
    const val Android = "io.ktor:ktor-client-android:1.5.0"
    const val Serialization = "io.ktor:ktor-client-serialization:1.5.0"
    const val Logging = "io.ktor:ktor-client-logging-jvm:1.5.0"
}