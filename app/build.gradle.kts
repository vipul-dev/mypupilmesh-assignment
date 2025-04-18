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



    implementation("androidx.navigation:navigation-compose:2.8.9")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")


    // Hilt ViewModel support (if needed)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    // Room
    implementation("androidx.room:room-runtime:2.6.1") 
    kapt("androidx.room:room-compiler:2.6.1")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Okhttp (for logging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // coil for network image
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Camerax core library
    implementation("androidx.camera:camera-core:1.4.2")

    //CameraX Camera2 extensions
    implementation("androidx.camera:camera-extensions:1.4.2")
    implementation("androidx.camera:camera-camera2:1.4.2")

    //CameraX lifecycle library
    implementation("androidx.camera:camera-lifecycle:1.4.2")

    //CameraX view class
    implementation("androidx.camera:camera-view:1.4.2")
    implementation("androidx.compose.ui:ui-viewbinding:1.6.0")


    // MediaPipe for face detection
    implementation("com.google.mediapipe:tasks-vision:0.10.14")

    // Permission library
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
}