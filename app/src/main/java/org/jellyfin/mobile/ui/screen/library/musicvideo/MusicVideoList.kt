package org.jellyfin.mobile.ui.screen.library.musicvideo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.jellyfin.mobile.R
import org.jellyfin.mobile.model.CollectionType
import org.jellyfin.mobile.model.dto.FolderInfo
import org.jellyfin.mobile.model.dto.MusicVideo
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.ui.screen.library.BaseMediaItem
import org.jellyfin.mobile.ui.utils.GridListFor
import timber.log.Timber

@Composable
fun MusicVideoList(viewModel: MusicVideoViewModel) {
    Timber.d(viewModel.contents.joinToString())
    GridListFor(items = viewModel.contents) { info ->
        when (info) {
            is FolderInfo -> FolderItem(folderInfo = info, modifier = Modifier.fillItemMaxWidth())
            is MusicVideo -> MusicVideoItem(musicVideo = info, modifier = Modifier.fillItemMaxWidth())
        }
    }
}

@Composable
fun FolderItem(folderInfo: FolderInfo, modifier: Modifier = Modifier) {
    BaseMediaItem(
        modifier = modifier,
        id = folderInfo.id,
        title = folderInfo.name,
        primaryImageTag = folderInfo.primaryImageTag,
        imageDecorator = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_folder_white_24dp),
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.4f), CircleShape)
                        .padding(6.dp),
                    contentDescription = null,
                )
            }
        },
        onClick = {
            val info = UserViewInfo(folderInfo.id, folderInfo.name, CollectionType.MusicVideos, folderInfo.primaryImageTag)
            // TODO: navigate to library
        },
    )
}

@Composable
fun MusicVideoItem(musicVideo: MusicVideo, modifier: Modifier = Modifier) {
    BaseMediaItem(
        modifier = modifier,
        id = musicVideo.id,
        title = musicVideo.title,
        subtitle = musicVideo.album,
        primaryImageTag = musicVideo.primaryImageTag,
        imageDecorator = {
            // TODO: add watched state
        },
    )
}
