import com.dhimandasgupta.buildsrc.AndroidX
import com.dhimandasgupta.buildsrc.App
import com.dhimandasgupta.buildsrc.Kotlin

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    compileSdk = App.COMPILE_VERSION_SDK

    defaultConfig {
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        version = App.VERSION_CODE

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(Kotlin.KOTLIN_STD_LIB)
    implementation(Kotlin.Coroutine.COROUTINE_CORE)
}