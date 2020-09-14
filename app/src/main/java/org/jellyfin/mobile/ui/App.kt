package org.jellyfin.mobile.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.github.zsoltk.compose.router.BackStack
import com.github.zsoltk.compose.router.Router
import org.jellyfin.mobile.controller.ServerController
import org.jellyfin.mobile.model.dto.AlbumInfo
import org.jellyfin.mobile.model.dto.ArtistInfo
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.model.state.LoginState
import org.jellyfin.mobile.ui.screen.SetupScreen
import org.jellyfin.mobile.ui.screen.home.HomeScreen
import org.jellyfin.mobile.ui.screen.library.LibraryScreen
import org.jellyfin.mobile.ui.screen.library.music.AlbumScreen
import org.jellyfin.mobile.ui.screen.library.music.ArtistScreen

@Composable
fun AppContent() {
    val serverController: ServerController by inject()
    Crossfade(targetState = serverController.loginState) { loginState ->
        when (loginState) {
            LoginState.PENDING -> Unit // do nothing
            LoginState.NOT_LOGGED_IN -> injectContent<SetupScreen>()
            LoginState.LOGGED_IN -> AppRouter()
        }
    }
}

val LocalBackStack = staticCompositionLocalOf<BackStack<Routing>> { throw IllegalStateException() }

@Composable
fun AppRouter() {
    Router<Routing>("App", Routing.Home) { backStack ->
        CompositionLocalProvider(LocalBackStack provides backStack) {
            Crossfade(targetState = backStack.last()) { route ->
                when (route) {
                    is Routing.Home -> injectContent<HomeScreen>()
                    is Routing.Library -> remember(route.info) { LibraryScreen(route.info) }.Content()
                    is Routing.Album -> remember(route.info) { AlbumScreen(route.info) }.Content()
                    is Routing.Artist -> remember(route.info) { ArtistScreen(route.info) }.Content()
                }
            }
        }
    }
}

sealed class Routing {
    object Home : Routing()
    class Library(val info: UserViewInfo) : Routing()
    class Album(val info: AlbumInfo) : Routing()
    class Artist(val info: ArtistInfo) : Routing()
}
