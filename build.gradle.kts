// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Jetbrains.Kover.Plugin) version Jetbrains.Kover.Version
    id(Jetbrains.Dokka.Plugin) version Jetbrains.Dokka.Version
    id(Ksp.Plugin) version Ksp.Version apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Android.Tools.GradlePlugin)
        classpath(Jetbrains.Kotlin.GradlePlugin)
        classpath(Google.Dagger.GradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
// TODO: FIX Dokka and Kover
//tasks.dokkaHtmlMultiModule.configure {
//    outputDirectory.set(layout.buildDirectory.resolve("dokka"))
//}
//tasks.koverMergedHtmlReport {
//    isEnabled = true
//    htmlReportDir.set(layout.buildDirectory.dir("kover"))
//}