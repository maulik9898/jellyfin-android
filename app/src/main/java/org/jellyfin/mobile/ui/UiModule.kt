package org.jellyfin.mobile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jellyfin.mobile.ui.screen.AbstractScreen
import org.jellyfin.mobile.ui.screen.SetupScreen
import org.jellyfin.mobile.ui.screen.home.HomeScreen
import org.koin.core.context.KoinContextHandler
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

val uiModule = module {
    single { SetupScreen() }
    single { HomeScreen(get(), get()) }
}

@Composable
inline fun <reified T> inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> = remember {
    val context = KoinContextHandler.get()
    context.inject(qualifier, parameters)
}

@Composable
inline fun <reified T : AbstractScreen> injectContent() {
    val screen: T by inject()
    screen.Content()
}
