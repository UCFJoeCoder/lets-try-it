import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("app.cash.sqldelight")
}

android {
    namespace = "com.ucfjoe.letstryit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ucfjoe.letstryit"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val keyStoreFile = project.rootProject.file("apikey.properties")
        val properties = Properties()
        properties.load(keyStoreFile.inputStream())
        buildConfigField(
            "String",
            "MARVEL_API_KEY",
            properties.getProperty("MARVEL_API_KEY") ?: "")
        buildConfigField(
            "String",
            "MARVEL_PRIVATE_KEY",
            properties.getProperty("MARVEL_PRIVATE_KEY") ?: ""
        )
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // NavController
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")

    // Lifecycle ViewModel and Runtime dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")

    // Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // GSON
    implementation("com.google.code.gson:gson:2.10.1")

    // Coil (for async image downloading)
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Room database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // SQL Delight
//    implementation("com.squareup.sqldelight:android-driver:1.5.3")
//    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.3")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
    implementation("app.cash.sqldelight:android-driver:2.0.1")
}

sqldelight {
    databases {
        create("TeamDatabase") {
            packageName.set("com.ucfjoe.letstryit")
        }
    }
}