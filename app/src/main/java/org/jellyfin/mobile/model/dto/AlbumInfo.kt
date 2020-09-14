package org.jellyfin.mobile.model.dto

import androidx.compose.runtime.Immutable
import org.jellyfin.apiclient.model.dto.BaseItemType

@Immutable
data class AlbumInfo(
    override val id: String,
    val name: String,
    val artist: String,
    override val primaryImageTag: String?,
) : BaseMediaInfo {
    override val itemType: BaseItemType = BaseItemType.MusicAlbum
    override val title: String = name
    override val subtitle: String? = artist
}
