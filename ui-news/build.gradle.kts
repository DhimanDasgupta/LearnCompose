import com.dhimandasgupta.buildsrc.AndroidX
import com.dhimandasgupta.buildsrc.App

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
    implementation(project(path = ":commom-android"))
    implementation(project(path = ":ui-theme"))
    implementation(project(path = ":ui-composables"))
    implementation(project(path = ":data-news"))

    api(AndroidX.Compose.TOOL)
    api(AndroidX.WINDOW)
}