package org.jellyfin.mobile.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jellyfin.apiclient.interaction.ApiClient
import org.jellyfin.apiclient.model.dto.BaseItemDto
import org.jellyfin.apiclient.model.entities.CollectionType
import org.jellyfin.apiclient.model.entities.ImageType
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.utils.getUserViews

class LibraryController(
    private val apiClient: ApiClient,
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    var userViews: List<UserViewInfo> by mutableStateOf(emptyList())

    init {
        scope.launch {
            userViews = apiClient.getUserViews()?.run {
                items.map(BaseItemDto::toUserViewInfo).filter { item -> item.collectionType in SUPPORTED_COLLECTION_TYPES }
            } ?: emptyList()
        }
    }


    companion object {
        val SUPPORTED_COLLECTION_TYPES = setOf(
            CollectionType.Movies,
            CollectionType.TvShows,
            CollectionType.Music,
            CollectionType.MusicVideos,
        )
    }
}

private fun BaseItemDto.toUserViewInfo() = UserViewInfo(id, name, collectionType, imageTags[ImageType.Primary])
