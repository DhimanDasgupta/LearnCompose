import com.dhimandasgupta.buildsrc.Accompanist
import com.dhimandasgupta.buildsrc.AndroidX
import com.dhimandasgupta.buildsrc.App
import com.dhimandasgupta.buildsrc.Material3
import com.dhimandasgupta.buildsrc.Others

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Apis
    api(AndroidX.WINDOW)
    api(AndroidX.Compose.ANIMATION)
    api(AndroidX.Compose.COMPILER)
    api(AndroidX.Compose.FOUNDATION)
    api(AndroidX.Compose.MATERIAL)
    api(AndroidX.Compose.RUNTIME)
    api(AndroidX.Compose.UI)
    api(AndroidX.Compose.TOOL)
    // api(AndroidX.Compose.TOOL_PREVIEW)
    api(Material3.MATERIAL3)

    // Accompanist
    api(Accompanist.APPCOMPAT_THEME)
    api(Accompanist.DRAWABLE_PAINTER)
    api(Accompanist.FLOW_LAYOUT)
    api(Accompanist.INSET_UI)
    api(Accompanist.INSETS)
    api(Accompanist.NAVIGATION_ANIMATION)
    api(Accompanist.NAVIGATION_MATERIAL)
    api(Accompanist.PAGER_INDICATORS)
    api(Accompanist.PAGER)
    api(Accompanist.PERMISSIONS)
    api(Accompanist.PLACEHOLDER_MATERIAL)
    api(Accompanist.PLACEHOLDER)
    api(Accompanist.SWIPE_REFRESH)
    api(Accompanist.SYSTEM_UI_CONTROLLER)

    // Navigation
    api(AndroidX.COMPOSE_NAVIGATION)

    // Coil
    api(Others.COIL)

    // Hilt
//    api(AndroidX.Hilt.VIEW_MODEL)
    api(AndroidX.Hilt.NAVIGATION)

    // Debug apis
    // debugApi(AndroidX.Compose.TOOL)

    // Test apis
    androidTestApi(AndroidX.Compose.TEST_UI)
}