plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")



}

    android {
        namespace = "com.mahin.movieplx"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.mahin.movieplx"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
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

        // Navigation
        implementation("androidx.navigation:navigation-compose:2.8.3")

        // Room
        implementation("androidx.room:room-ktx:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")
        implementation("androidx.room:room-paging:2.6.1")

        //Shimmer
        implementation("com.valentinilk.shimmer:compose-shimmer:1.3.1")

        //lifecycle
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

        // Coil
        implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")

        // Dagger - Hilt
        implementation (libs.hilt.android)
        kapt (libs.hilt.compiler)
        kapt("androidx.hilt:hilt-compiler:1.2.0")
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

        // Retrofit
        implementation ("com.squareup.retrofit2:retrofit:2.11.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:okhttp:4.12.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

        // Extended Icons
        implementation("androidx.compose.material:material-icons-extended:1.7.4")

        // system UI Controller
        implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

    }
