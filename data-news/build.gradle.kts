import com.dhimandasgupta.buildsrc.Kotlin

plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Kotlin.KOTLIN_STD_LIB)
    implementation(Kotlin.Ktor.CORE)
    implementation(Kotlin.Ktor.CIO)
    implementation(Kotlin.Ktor.ANDROID)
    implementation(Kotlin.Ktor.OK_HTTP)
    implementation(Kotlin.Ktor.CLIENT_LOGGING)
    implementation(Kotlin.Ktor.CLIENT_SERIALIZATION)
    implementation(Kotlin.Ktor.CLIENT_SERIALIZATION_JVM)
    implementation(Kotlin.Ktor.SERIALIZATION_JSON)

    implementation(Kotlin.DateTime.KTX_DATE_TIME)
    implementation(Kotlin.Logger.LOGBACK_CLASSIC)
}