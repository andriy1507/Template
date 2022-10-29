// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Jetbrains.Kover.Plugin) version Jetbrains.Kover.Version
    id(Jetbrains.Dokka.Plugin) version KotlinVersion
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
    delete(rootProject.buildDir)
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}
tasks.koverMergedHtmlReport {
    isEnabled = true
    htmlReportDir.set(layout.buildDirectory.dir("kover"))
}