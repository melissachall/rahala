plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.google.gms.google.services)
    //id("com.android.application")
    id("com.google.gms.google-services")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.rahala"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rahala"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    kotlinOptions {
        jvmTarget = "19"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    //implementation("com.google.firebase:firebase-auth-ktx") // Exemple pour Firebase Auth
    //implementation("com.google.firebase:firebase-firestore-ktx") // Exemple pour Firestore
    //implementation("com.google.android.gms:play-services-auth:22.3.0") // Pour Google Sign-In
    //implementation("com.google.android.gms:play-services-identity:18.1.0")

    implementation("com.google.firebase:firebase-auth:23.2.0")
    implementation("com.google.firebase:firebase-firestore:25.1.2")
    implementation("com.google.firebase:firebase-messaging:24.1.0")

    implementation ("androidx.compose.foundation:foundation:1.7.8")

    //FIREBASE
    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
    //implementation("com.google.firebase:firebase-analytics")



    // Compose BOM (gère automatiquement les versions compatibles)
    implementation(platform("androidx.compose:compose-bom:2025.02.00"))

    // Material (si tu utilises Material 2)
    implementation("androidx.compose.material:material")
    implementation ("androidx.compose.material3:material3")
    // Jetpack Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.8")

    implementation ("com.google.accompanist:accompanist-pager:0.36.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.36.0")
    implementation ("androidx.compose.ui:ui-text:1.7.8")






    // Activité Compose
    implementation("androidx.activity:activity-compose:1.10.1")
    //implementation(libs.firebase.auth)

    // Tests debug
    debugImplementation("androidx.compose.ui:ui-tooling")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}

apply(plugin = "com.google.gms.google-services")