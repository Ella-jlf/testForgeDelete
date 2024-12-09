plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.delete.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.delete.test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


    }

    buildTypes {
        release {
            applicationIdSuffix = ".release"
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "server_url", "\"https://api.apilayer.com/exchangerates_data/\"")
            buildConfigField("String", "authToken", "\"PO3zBaqt6Hlt5azQKNYeslvn4C8VtgYa\"")
        }

        debug {
            applicationIdSuffix = ".debug"
            buildConfigField("String", "server_url", "\"https://api.apilayer.com/exchangerates_data/\"")
            buildConfigField("String", "authToken", "\"PO3zBaqt6Hlt5azQKNYeslvn4C8VtgYa\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        compose = true
        buildConfig = true
        resValues = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging{
        resources{
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.layout)
    implementation(libs.compose.material)
    implementation(libs.compose.material.three)

    implementation(libs.arrow.core)

    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.http.interceptor)
    implementation(libs.gson)

    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.androidx.activity)
    ksp(libs.room.compiler)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.navigation)

    //implementation(libs.dagger)
    //kapt(libs.dagger.compiler)
   //implementation(libs.dagger.android)
    //kapt(libs.dagger.compiler.android)

}