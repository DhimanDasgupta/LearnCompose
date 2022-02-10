package com.dhimandasgupta.buildsrc

object AndroidX {
    const val CORE_SPLASH_SCREEN = "androidx.core:core-splashscreen:1.0.0-beta01"
    const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.0"
    const val MATERIAL = "com.google.android.material:material:1.5.0-alpha05"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.4.0"

    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:2.4.0-alpha01"
    const val COMPOSE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"
    const val VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"

    const val WINDOW = "androidx.window:window:1.0.0"

    object Hilt {
        private const val version = "1.0.0-beta01"

        // const val VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
        const val NAVIGATION = "androidx.hilt:hilt-navigation-compose:$version"
    }

    object Compose {
        const val VERSION = "1.1.0-rc01"
        const val KOTLIN_COMPILER_VERSION = "1.6.0"

        // Apis
        const val ANIMATION = "androidx.compose.animation:animation:$VERSION"
        const val COMPILER = "androidx.compose.compiler:compiler:$VERSION"
        const val FOUNDATION = "androidx.compose.foundation:foundation:$VERSION"
        const val MATERIAL = "androidx.compose.material:material:$VERSION"
        const val RUNTIME = "androidx.compose.runtime:runtime:$VERSION"
        const val UI = "androidx.compose.ui:ui:$VERSION"

        // Debug apis
        const val TOOL = "androidx.compose.ui:ui-tooling:$VERSION"
        const val TOOL_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$VERSION"

        // Test apis
        const val TEST_UI = "androidx.compose.ui:ui-test-junit4:$VERSION"
    }
}
