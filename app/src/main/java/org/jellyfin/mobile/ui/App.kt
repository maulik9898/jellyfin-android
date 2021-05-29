package org.jellyfin.mobile.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import org.jellyfin.mobile.controller.LoginController
import org.jellyfin.mobile.model.state.LoginState
import org.jellyfin.mobile.ui.AppDestinations.ROUTE_ALBUM
import org.jellyfin.mobile.ui.AppDestinations.ROUTE_ARTIST
import org.jellyfin.mobile.ui.AppDestinations.ROUTE_COLLECTION
import org.jellyfin.mobile.ui.AppDestinations.ROUTE_HOME
import org.jellyfin.mobile.ui.AppDestinations.ROUTE_UUID_KEY
import org.jellyfin.mobile.ui.screen.SetupScreen
import org.jellyfin.mobile.ui.screen.home.HomeScreen
import org.jellyfin.sdk.model.serializer.toUUIDOrNull
import java.util.UUID

@Composable
fun AppContent() {
    val loginController: LoginController by inject()
    Crossfade(targetState = loginController.loginState) { loginState ->
        when (loginState) {
            LoginState.PENDING -> Unit // do nothing
            LoginState.NOT_LOGGED_IN -> SetupScreen()
            LoginState.LOGGED_IN -> AppRouter()
        }
    }
}

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_HOME) {
        composable(ROUTE_HOME) {
            HomeScreen()
        }
        composableWithUuidArgument(ROUTE_COLLECTION) { _, uuid ->
            //remember(route.info) { LibraryScreen(route.info) }.Content()
        }
        composableWithUuidArgument(ROUTE_ALBUM) { _, uuid ->
            //remember(route.info) { AlbumScreen(route.info) }.Content()
        }
        composableWithUuidArgument(ROUTE_ARTIST) { _, uuid ->
            //remember(route.info) { ArtistScreen(route.info) }.Content()
        }
    }
}

fun NavGraphBuilder.composableWithUuidArgument(
    route: String,
    content: @Composable (backStackEntry: NavBackStackEntry, uuid: UUID) -> Unit,
) {
    composable(
        route = "$route/{$ROUTE_UUID_KEY}",
        arguments = listOf(
            navArgument(ROUTE_UUID_KEY) { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val uuid = requireNotNull(arguments.getString(ROUTE_UUID_KEY)?.toUUIDOrNull())
        content(backStackEntry, uuid)
    }
}

object AppDestinations {
    const val ROUTE_HOME = "home"
    const val ROUTE_COLLECTION = "collection"
    const val ROUTE_ALBUM = "album"
    const val ROUTE_ARTIST = "artist"

    const val ROUTE_UUID_KEY = "uuid"
}
