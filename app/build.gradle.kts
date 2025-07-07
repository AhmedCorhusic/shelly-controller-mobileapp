import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "ba.ahmed.shelly_controller_mobileapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "ba.ahmed.shelly_controller_mobileapp"
        minSdk = 26
        //noinspection EditedTargetSdkVersion
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        //load the values from .properties file
        val keystoreFile = project.rootProject.file(".properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        //return empty key in case something goes wrong
        val cloadApiKey = properties.getProperty("CLOUD_API_KEY") ?: ""
        buildConfigField(
            type = "String",
            name = "CLOUD_API_KEY",
            value = cloadApiKey
        )

        val host1ID = properties.getProperty("ID_HOST1") ?: ""
        buildConfigField(
            type = "String",
            name = "ID_HOST1",
            value = host1ID
        )

        val host2ID = properties.getProperty("ID_HOST2") ?: ""
        buildConfigField(
            type = "String",
            name = "ID_HOST2",
            value = host2ID
        )

        val host3ID = properties.getProperty("ID_HOST3") ?: ""
        buildConfigField(
            type = "String",
            name = "ID_HOST3",
            value = host3ID
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.cardview)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}