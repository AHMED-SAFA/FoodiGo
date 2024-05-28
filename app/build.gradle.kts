val androidTestImplementation: Unit = Unit

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.android.foodiego"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android.foodiego"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

//dependencies {
//
//// Glide for image loading
//    implementation("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
//
//    // AndroidX and Firebase dependencies
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.activity:activity:1.8.0")
//    implementation("androidx.exifinterface:exifinterface:1.3.6")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation("com.google.firebase:firebase-firestore:24.11.1")
//    implementation("com.google.firebase:firebase-auth:22.3.1")
//    implementation("com.google.firebase:firebase-database:20.3.1")
//    implementation("com.google.firebase:firebase-storage:20.3.0")
//
//    // Image picker and image loading libraries
//    implementation("com.github.dhaval2404:imagepicker:2.1")
//    implementation("com.squareup.picasso:picasso:2.71828")
//
//    // Testing dependencies
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test:runner:1.4.0")
//    androidTestImplementation("androidx.test:rules:1.4.0")
//    implementation("androidx.test:core:1.5.0")
//
//}

//dependencies {
//    // Glide for image loading
//    implementation("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
//
//    // AndroidX and Firebase dependencies
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.activity:activity:1.8.0")
//    implementation("androidx.exifinterface:exifinterface:1.3.6")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation("com.google.firebase:firebase-firestore:24.11.1")
//    implementation("com.google.firebase:firebase-auth:22.3.1")
//    implementation("com.google.firebase:firebase-database:20.3.1")
//    implementation("com.google.firebase:firebase-storage:20.3.0")
//
//    // Image picker and image loading libraries
//    implementation("com.github.dhaval2404:imagepicker:2.1")
//    implementation("com.squareup.picasso:picasso:2.71828")
//
//    // Testing dependencies
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test:runner:1.4.0")
//    androidTestImplementation("androidx.test:rules:1.4.0")
//    implementation("androidx.test:core:1.5.0")
//}

dependencies {
    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.14.2") {
        exclude(group = "com.android.support")
    }
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2") {
        exclude(group = "com.android.support")
    }

    // AndroidX and Firebase dependencies
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.google.firebase:firebase-firestore:24.11.1")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")

    // Image picker and image loading libraries
    implementation("com.github.dhaval2404:imagepicker:2.1") {
        exclude(group = "com.android.support")
    }
    implementation("com.squareup.picasso:picasso:2.71828") {
        exclude(group = "com.android.support")
    }

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    implementation("androidx.test:core:1.5.0")
}

// Add this to enforce using AndroidX libraries
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.android.support") {
            useVersion("1.0.0")
            because("Forcing the use of AndroidX libraries to avoid conflicts with com.android.support")
        }
    }
}

