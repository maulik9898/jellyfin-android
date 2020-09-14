package org.jellyfin.mobile.ui.screen.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import org.jellyfin.apiclient.model.entities.CollectionType
import org.jellyfin.mobile.R
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.ui.ScreenScaffold
import org.jellyfin.mobile.ui.screen.AbstractScreen
import org.jellyfin.mobile.ui.screen.library.music.AlbumList
import org.jellyfin.mobile.ui.screen.library.music.ArtistList
import org.jellyfin.mobile.ui.screen.library.music.MusicViewModel
import org.jellyfin.mobile.ui.screen.library.music.SongList
import org.jellyfin.mobile.ui.screen.library.musicvideo.MusicVideoList
import org.jellyfin.mobile.ui.screen.library.musicvideo.MusicVideoViewModel

class LibraryScreen(private val viewInfo: UserViewInfo) : AbstractScreen() {

    @Composable
    override fun Content() {
        ScreenScaffold(
            title = viewInfo.name,
            canGoBack = true,
            hasElevation = false,
        ) {
            when (viewInfo.collectionType) {
                CollectionType.Music -> MusicLibraryContent()
                CollectionType.MusicVideos -> MusicVideoLibraryContent()
            }
        }
    }

    @Composable
    fun MusicLibraryContent() {
        val viewModel = remember { MusicViewModel(viewInfo) }
        val tabTitles = remember {
            listOf(R.string.library_music_tab_albums, R.string.library_music_tab_artists, R.string.library_music_tab_songs)
        }.map { id -> stringResource(id) }
        TabbedContent(
            tabTitles = tabTitles,
            currentTabState = viewModel.currentTab,
        ) { page ->
            when (page) {
                0 -> AlbumList(albums = viewModel.albums)
                1 -> ArtistList(artists = viewModel.artists)
                2 -> SongList(songs = viewModel.songs)
            }
        }
    }

    @Composable
    fun MusicVideoLibraryContent() {
        val viewModel = remember { MusicVideoViewModel(viewInfo) }
        MusicVideoList(viewModel = viewModel)
    }

    /*@Composable
    inline fun <reified T : LibraryViewModel> libraryViewModel(viewInfo: UserViewInfo): T = viewModel(
        key = "library_${viewInfo.collectionType}_${viewInfo.id}",
        factory = viewModelProviderFactoryOf {
            when (viewInfo.collectionType) {
                CollectionType.Music -> MusicViewModel(viewInfo)
                CollectionType.MusicVideos -> MusicVideoViewModel(viewInfo)
                else -> throw IllegalArgumentException("Unsupported collection type ${viewInfo.collectionType}")
            }
        }
    )*/
}
