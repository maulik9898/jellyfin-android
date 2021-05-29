package org.jellyfin.mobile.ui.screen.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import org.jellyfin.mobile.R
import org.jellyfin.mobile.model.CollectionType
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.ui.ScreenScaffold
import org.jellyfin.mobile.ui.screen.library.music.AlbumList
import org.jellyfin.mobile.ui.screen.library.music.ArtistList
import org.jellyfin.mobile.ui.screen.library.music.MusicViewModel
import org.jellyfin.mobile.ui.screen.library.music.SongList
import org.jellyfin.mobile.ui.screen.library.musicvideo.MusicVideoList
import org.jellyfin.mobile.ui.screen.library.musicvideo.MusicVideoViewModel

@Composable
fun LibraryScreen(view: UserViewInfo) {
    ScreenScaffold(
        title = view.name,
        canGoBack = true,
        hasElevation = false,
    ) {
        when (view.collectionType) {
            CollectionType.Music -> MusicLibraryContent(view)
            CollectionType.MusicVideos -> MusicVideoLibraryContent(view)
        }
    }
}

@Composable
fun MusicLibraryContent(view: UserViewInfo) {
    val viewModel = remember { MusicViewModel(view) }
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
fun MusicVideoLibraryContent(view: UserViewInfo) {
    val viewModel = remember { MusicVideoViewModel(view) }
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
