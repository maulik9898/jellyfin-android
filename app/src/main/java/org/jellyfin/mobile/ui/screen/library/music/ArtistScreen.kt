package org.jellyfin.mobile.ui.screen.library.music

import androidx.compose.runtime.Composable
import org.jellyfin.mobile.model.dto.ArtistInfo
import org.jellyfin.mobile.ui.ScreenScaffold
import org.jellyfin.mobile.ui.screen.AbstractScreen

class ArtistScreen(private val viewInfo: ArtistInfo) : AbstractScreen() {

    @Composable
    override fun Content() {
        ScreenScaffold(
            title = viewInfo.name,
            canGoBack = true,
            hasElevation = false,
        ) {

        }
    }
}
