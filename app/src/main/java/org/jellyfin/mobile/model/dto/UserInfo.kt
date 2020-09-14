package org.jellyfin.mobile.model.dto

import androidx.compose.runtime.Immutable
import org.jellyfin.apiclient.model.dto.UserDto

@Immutable
data class UserInfo(
    val id: Long,
    val userId: String,
    val name: String,
    val primaryImageTag: String?,
) {
    constructor(id: Long, dto: UserDto): this(id, dto.id, dto.name, dto.primaryImageTag)
}
