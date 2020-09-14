package org.jellyfin.mobile.ui.screen.library.musicvideo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jellyfin.apiclient.model.dto.BaseItemType
import org.jellyfin.apiclient.model.entities.CollectionType
import org.jellyfin.apiclient.model.querying.ItemQuery
import org.jellyfin.apiclient.model.querying.ItemSortBy
import org.jellyfin.mobile.model.dto.BaseMediaInfo
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.model.dto.toFolderInfo
import org.jellyfin.mobile.model.dto.toMusicVideoInfo
import org.jellyfin.mobile.ui.screen.library.LibraryViewModel
import org.jellyfin.mobile.utils.getItems

class MusicVideoViewModel(viewInfo: UserViewInfo) : LibraryViewModel(viewInfo) {
    val contents = mutableStateListOf<BaseMediaInfo>()

    init {
        require(viewInfo.collectionType == CollectionType.MusicVideos) {
            "Invalid ViewModel for collection type ${viewInfo.collectionType}"
        }

        viewModelScope.launch {
            launch {
                apiClient.getItems(buildItemQuery(viewInfo.id))?.run {
                    contents += items.map { item ->
                        when (item.baseItemType) {
                            BaseItemType.Folder -> item.toFolderInfo()
                            else -> item.toMusicVideoInfo()
                        }
                    }
                }
            }
        }
    }

    private fun buildItemQuery(id: String) = ItemQuery().apply {
        userId = apiClient.currentUserId
        parentId = id
        sortBy = arrayOf(ItemSortBy.IsFolder, ItemSortBy.SortName)
        startIndex = 0
        limit = 100
    }
}
