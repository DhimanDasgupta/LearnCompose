package com.dhimandasgupta.buildsrc

object Api {
    object Okhttp {
        private const val VERSION = "4.9.0"

        const val OK_HTTP = "com.squareup.okhttp3:okhttp:$VERSION"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$VERSION"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:$VERSION"
    }

    object Retrofit {
        private const val VERSION = "2.9.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$VERSION"
    }
}