object Dependencies {
    object Versions {
        // Gradle plugins
        const val dependencyUpdates = "0.38.0"
        const val detekt = "1.17.1"

        // KotlinX
        const val coroutines = "1.5.0"

        // Core
        const val koin = "2.1.6"
        const val appCompat = "1.3.0"
        const val androidxCore = "1.5.0"
        const val activity = "1.2.3"
        const val fragment = "1.3.4"
        const val exoPlayer = "2.13.3"

        // Lifecycle
        const val lifecycleExtensions = "2.3.1"

        // UI
        const val constraintLayout = "2.0.4"
        const val material = "1.3.0"
        const val palette = "1.0.0"
        const val webkitX = "1.4.0"
        const val modernAndroidPreferences = "2.1.0"

        // Compose
        const val compose = "1.0.0-SNAPSHOT"
        const val navigationCompose = "2.4.0-alpha01"
        const val accompanist = "0.10.0"

        // Room
        const val room = "2.3.0"

        // Network
        const val jellyfinSdk = "1.0.0-beta.7"
        const val jellyfinSdkSnapshot = "master-SNAPSHOT"
        const val okHttp = "4.9.1"
        const val coil = "1.1.1"

        // Cast
        const val mediaRouter = "1.2.3"
        const val playServicesCast = "19.0.0"

        // Media
        const val media = "1.3.1"

        // Health
        const val timber = "4.7.1"
        const val leakCanary = "2.7"
        const val redScreenOfDeath = "0.1.3"
        const val junit = "5.7.2"
        const val kotest = "4.6.0"
        const val mockk = "1.11.0"
        const val androidXRunner = "1.3.0"
        const val androidXEspresso = "3.3.0"
        const val androidDesugarLibs = "1.1.5"
    }

    object Groups {
        const val jellyfin = "org.jellyfin.sdk"
    }

    object Kotlin {
        val coroutinesCore = kotlinx("coroutines-core", Versions.coroutines)
        val coroutinesAndroid = kotlinx("coroutines-android", Versions.coroutines)
    }

    object Core {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinViewModel = "io.insert-koin:koin-android-viewmodel:${Versions.koin}"
        val appCompat = androidx("appcompat", Versions.appCompat)
        val androidx = androidxKtx("core", Versions.androidxCore)
        val activity = androidxKtx("activity", Versions.activity)
        val fragment = androidxKtx("fragment", Versions.fragment)
        const val koinFragment = "io.insert-koin:koin-androidx-fragment:${Versions.koin}"
        val exoPlayer = exoPlayer("core")
    }

    object Lifecycle {
        val viewModel = lifecycle("viewmodel-ktx")
        val liveData = lifecycle("livedata-ktx")
        val runtime = lifecycle("runtime-ktx")
        val common = lifecycle("common-java8")
        val process = lifecycle("process")
        val compose = lifecycle("viewmodel-compose", "1.0.0-alpha05")
    }

    object UI {
        val constraintLayout = androidx("constraintlayout", Versions.constraintLayout)
        const val material = "com.google.android.material:material:${Versions.material}"
        val palette = androidx("palette", Versions.palette)
        val webkitX = androidx("webkit", Versions.webkitX)
        val exoPlayer = exoPlayer("ui")
        const val modernAndroidPreferences = "de.maxr1998:modernandroidpreferences:${Versions.modernAndroidPreferences}"
    }

    object Compose {
        val runtime = compose("runtime")
        val ui = compose("ui")
        val uiTooling = compose("ui", "tooling")
        val foundation = compose("foundation")
        val animation = compose("animation")
        val material = compose("material")
        val materialIcons = compose("material", "icons-core")
        val materialIconsExtended = compose("material", "icons-extended")
        val runtimeLiveData = compose("runtime", "livedata")
        val navigation = androidx("navigation", "compose", Versions.navigationCompose)
        const val accompanistCoil = "com.google.accompanist:accompanist-coil:${Versions.accompanist}"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    }

    object Room {
        val runtime = room("runtime")
        val compiler = room("compiler")
    }

    object Network {
        const val jellyfinSdk = "${Groups.jellyfin}:jellyfin-platform-android:${Versions.jellyfinSdk}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        val exoPlayerHLS = exoPlayer("hls")
    }

    object Cast {
        val mediaRouter = androidx("mediarouter", Versions.mediaRouter)
        const val exoPlayerCastExtension = "com.google.android.exoplayer:extension-cast:${Versions.exoPlayer}"
        const val playServicesCast = "com.google.android.gms:play-services-cast:${Versions.playServicesCast}"
        const val playServicesCastFramework = "com.google.android.gms:play-services-cast-framework:${Versions.playServicesCast}"
    }

    object Media {
        val media = androidx("media", Versions.media)
        var exoPlayerMediaSession = "com.google.android.exoplayer:extension-mediasession:${Versions.exoPlayer}"
    }

    /**
     * Includes logging, debugging, testing and desugaring
     */
    object Health {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
        const val redScreenOfDeath = "com.mlegy.redscreenofdeath:red-screen-of-death:${Versions.redScreenOfDeath}"
        const val redScreenOfDeathNoOp = "com.mlegy.redscreenofdeath:red-screen-of-death-no-op:${Versions.redScreenOfDeath}"
        const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
        const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
        const val kotestProperty = "io.kotest:kotest-property-jvm:${Versions.kotest}"
        const val kotestRunner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val androidXRunner = "androidx.test:runner:${Versions.androidXRunner}"
        const val androidXEspresso = "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
        const val androidDesugarLibs = "com.android.tools:desugar_jdk_libs:${Versions.androidDesugarLibs}"
    }

    // Helpers
    private fun androidx(module: String, version: String) = androidx(module, null, version)
    private fun androidx(module: String, variant: String?, version: String) = "androidx.$module:$module${variant?.let { "-$it" } ?: ""}:$version"
    private fun androidxKtx(module: String, version: String) = "androidx.$module:$module-ktx:$version"
    private fun kotlinx(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$module:$version"
    private fun lifecycle(module: String, version: String = Versions.lifecycleExtensions) = "androidx.lifecycle:lifecycle-$module:${version}"
    private fun room(module: String) = "androidx.room:room-$module:${Versions.room}"
    private fun compose(module: String, variant: String? = null) = "androidx.compose.$module:$module${variant?.let { "-$it" } ?: ""}:${Versions.compose}"
    private fun exoPlayer(module: String) = "com.google.android.exoplayer:exoplayer-$module:${Versions.exoPlayer}"
}
