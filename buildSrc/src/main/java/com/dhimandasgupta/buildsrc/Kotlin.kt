package com.dhimandasgupta.buildsrc

object Kotlin {
    const val VERSION = "1.6.10"
    const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$VERSION"
    const val KOTLIN_ANDROID_EXTENSION = "org.jetbrains.kotlin:kotlin-android-extensions:$VERSION"
    const val KOTLIN_STD_LIB_COMMON = "org.jetbrains.kotlin:kotlin-stdlib-common:$VERSION"

    object Coroutine {
        private const val VERSION = "1.6.0"

        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
    }

    object Serialization {
        const val VERSION = "1.6.0"
    }

    object Ktor {
        private const val VERSION = "1.6.7"

        const val CORE = "io.ktor:ktor-client-core:$VERSION"
        const val CIO = "io.ktor:ktor-client-cio:$VERSION"
        const val ANDROID = "io.ktor:ktor-client-android:$VERSION"
        const val OK_HTTP = "io.ktor:ktor-client-okhttp:$VERSION"
        const val CLIENT_LOGGING = "io.ktor:ktor-client-logging:$VERSION"
        const val CLIENT_SERIALIZATION = "io.ktor:ktor-client-serialization:$VERSION"
        const val CLIENT_SERIALIZATION_JVM = "io.ktor:ktor-client-serialization-jvm:$VERSION"
        const val SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    }

    object DateTime {
        private const val VERSION = "0.3.2"

        const val KTX_DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:$VERSION"
    }
}