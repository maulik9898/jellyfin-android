package org.jellyfin.mobile.model.dto

import org.jellyfin.apiclient.model.dto.BaseItemDto
import org.jellyfin.apiclient.model.entities.ImageType

fun BaseItemDto.toFolderInfo() = FolderInfo(id, name.orEmpty(), imageTags[ImageType.Primary])
fun BaseItemDto.toAlbumInfo() = AlbumInfo(id, name.orEmpty(), albumArtist.orEmpty(), imageTags[ImageType.Primary])
fun BaseItemDto.toArtistInfo() = ArtistInfo(id, name.orEmpty(), imageTags[ImageType.Primary])
fun BaseItemDto.toSongInfo() = SongInfo(id, albumId, name.orEmpty(), artists?.joinToString().orEmpty(), imageTags[ImageType.Primary])
fun BaseItemDto.toMusicVideoInfo() = MusicVideoInfo(id, name.orEmpty(), imageTags[ImageType.Primary])
