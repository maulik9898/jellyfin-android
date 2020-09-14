package org.jellyfin.mobile.model.dto

import androidx.compose.runtime.Immutable
import org.jellyfin.apiclient.model.dto.BaseItemType

@Immutable
data class SongInfo(
    override val id: String,
    val albumId: String,
    val name: String,
    val artist: String,
    override val primaryImageTag: String?,
) : BaseMediaInfo {
    override val parentId: String? = albumId
    override val itemType: BaseItemType = BaseItemType.Audio
    override val title: String = name
    override val subtitle: String? = artist
}
