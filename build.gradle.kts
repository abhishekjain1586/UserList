buildscript {
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:13.0")
        }
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization) apply false
    alias(libs.plugins.kotlinAndroidKsp) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
}
