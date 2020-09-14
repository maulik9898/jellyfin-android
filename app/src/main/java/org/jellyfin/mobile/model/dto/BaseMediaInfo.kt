package org.jellyfin.mobile.model.dto

import org.jellyfin.apiclient.model.dto.BaseItemType

interface BaseMediaInfo {
    val id: String
    val parentId: String?
        get() = null
    val itemType: BaseItemType
    val title: String
    val subtitle: String?
        get() = null
    val primaryImageTag: String?
    val watchedState: Boolean?
        get() = null
}
