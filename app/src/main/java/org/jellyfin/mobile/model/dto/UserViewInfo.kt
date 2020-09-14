package org.jellyfin.mobile.model.dto

import androidx.compose.runtime.Immutable

@Immutable
data class UserViewInfo(
    val id: String,
    val name: String,
    val collectionType: String,
    val primaryImageTag: String?,
)
