plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")



}

android {
    namespace = "com.vipul.dev.mypupilmesh"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vipul.dev.mypupilmesh"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt{
            arguments {
                arg("room.schemaLocation","$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation(libs.androidx.navigation)

    // Hilt
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)


    // Hilt ViewModel support (if needed)
    implementation(libs.android.hilt.navigation.compose)


    // Room
    implementation(libs.android.room.runtime)
    kapt(libs.android.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.android.room.ktx)

    // DataStore Preferences
    implementation(libs.android.datastore.pref)

    implementation(libs.android.kotlin.serialization)

    //Retrofit
    implementation(libs.android.retrofit)

    //Gson converter
    implementation(libs.android.retrofit.gson.converter)

    // Okhttp (for logging)
    implementation(libs.android.okhttp.logging)

    // coil for network image
    implementation(libs.android.coil)

    // Camerax core library
    implementation(libs.android.camera.core)

    //CameraX Camera2 extensions
    implementation(libs.android.camera.extensions)
    implementation(libs.android.camera.camera2)

    //CameraX lifecycle library
    implementation(libs.android.camera.lifecycle)

    //CameraX view class
    implementation(libs.android.camera.view)
    implementation(libs.android.compose.ui.viewbinding)


    // MediaPipe for face detection
    implementation(libs.mediapipe.task.vision)

    // Permission library
    implementation(libs.permission.library)
}