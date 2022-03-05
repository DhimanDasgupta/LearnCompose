import com.dhimandasgupta.buildsrc.AndroidX
import com.dhimandasgupta.buildsrc.App
import com.dhimandasgupta.buildsrc.Tests

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = App.COMPILE_VERSION_SDK

    defaultConfig {
        applicationId = App.APPLICATION_ID
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
    /*compileOptions {
        coreLibraryDesugaringEnabled = true
    }*/
}

dependencies {
    // Library
    implementation(project(path = ":ui-news"))
    implementation(project(path = ":ui-composables"))
    implementation(project(path = ":ui-theme"))
    implementation(project(path = ":data-news"))

    // Dependencies
    implementation(AndroidX.CORE_SPLASH_SCREEN)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APPCOMPAT)
    implementation(AndroidX.MATERIAL)
    implementation(AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(AndroidX.ACTIVITY_COMPOSE)
    implementation(AndroidX.VIEW_MODEL_KTX)
    implementation(AndroidX.COMPOSE_VIEW_MODEL)

    // Dagger & Hilt
    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-android-compiler:2.40")
    implementation("androidx.hilt:hilt-common:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // Test dependencies
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.JUNIT_ANDROID)
    androidTestImplementation(Tests.ESPRESSO_CORE)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}