package com.dhimandasgupta.buildsrc

object ClassPath {
    private const val ANDROID_GRADLE_PLUGIN_VERSION = "7.1.2"

    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:${ANDROID_GRADLE_PLUGIN_VERSION}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.VERSION}"
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.VERSION}"
    const val KOTLIN_SERIALIZATION_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.Serialization.VERSION}"
}