package org.jellyfin.mobile.ui.screen.library.music

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jellyfin.apiclient.model.dto.BaseItemDto
import org.jellyfin.apiclient.model.dto.BaseItemType
import org.jellyfin.apiclient.model.entities.CollectionType
import org.jellyfin.apiclient.model.querying.ArtistsQuery
import org.jellyfin.apiclient.model.querying.ItemQuery
import org.jellyfin.apiclient.model.querying.ItemSortBy
import org.jellyfin.mobile.model.dto.*
import org.jellyfin.mobile.ui.screen.library.LibraryViewModel
import org.jellyfin.mobile.utils.getAlbumArtists
import org.jellyfin.mobile.utils.getItems

class MusicViewModel(viewInfo: UserViewInfo) : LibraryViewModel(viewInfo) {
    val currentTab = mutableStateOf(0)
    val albums = mutableStateListOf<AlbumInfo>()
    val artists = mutableStateListOf<ArtistInfo>()
    val songs = mutableStateListOf<SongInfo>()

    init {
        require(viewInfo.collectionType == CollectionType.Music) {
            "Invalid ViewModel for collection type ${viewInfo.collectionType}"
        }

        viewModelScope.launch {
            launch {
                apiClient.getItems(buildItemQuery(BaseItemType.MusicAlbum))?.run {
                    albums += items.map(BaseItemDto::toAlbumInfo)
                }
            }
            launch {
                apiClient.getAlbumArtists(buildArtistsItemQuery())?.run {
                    artists += items.map(BaseItemDto::toArtistInfo)
                }
            }
            launch {
                apiClient.getItems(buildItemQuery(BaseItemType.Audio))?.run {
                    songs += items.map(BaseItemDto::toSongInfo)
                }
            }
        }
    }

    private fun buildItemQuery(itemType: BaseItemType) = ItemQuery().apply {
        userId = apiClient.currentUserId
        parentId = viewInfo.id
        includeItemTypes = arrayOf(itemType.name)
        recursive = true
        sortBy = arrayOf(ItemSortBy.SortName)
        startIndex = 0
        limit = 100
    }

    private fun buildArtistsItemQuery() = ArtistsQuery().apply {
        userId = apiClient.currentUserId
        parentId = viewInfo.id
        recursive = true
        sortBy = arrayOf(ItemSortBy.SortName)
        startIndex = 0
        limit = 100
    }
}
