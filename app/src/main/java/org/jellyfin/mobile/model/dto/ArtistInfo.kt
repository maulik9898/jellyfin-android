package org.jellyfin.mobile.model.dto

import androidx.compose.runtime.Immutable
import org.jellyfin.apiclient.model.dto.BaseItemType

@Immutable
data class ArtistInfo(
    override val id: String,
    val name: String,
    override val primaryImageTag: String?,
) : BaseMediaInfo {
    override val itemType: BaseItemType = BaseItemType.MusicArtist
    override val title: String = name
}
