package com.dhimandasgupta.buildsrc

object Tests {
    const val JUNIT = "junit:junit:4.13.2"
    const val JUNIT_ANDROID = "androidx.test.ext:junit:1.1.3"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"

    object MockK {
        private const val version = "1.10.2"

        const val MOCKK = "io.mockk:mockk:$version"
    }

    object Okhttp {
        private const val VERSION = "4.9.0"

        const val OK_HTTP = "com.squareup.okhttp3:okhttp:$VERSION"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:$VERSION"
    }

    object Coroutine {
        private const val VERSION = "1.5.2"

        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
    }
}