package org.jellyfin.mobile.model.dto

import org.jellyfin.apiclient.model.dto.BaseItemType

data class FolderInfo(
    override val id: String,
    val name: String,
    override val primaryImageTag: String?,
) : BaseMediaInfo {
    override val itemType: BaseItemType = BaseItemType.Folder
    override val title: String = name
}
