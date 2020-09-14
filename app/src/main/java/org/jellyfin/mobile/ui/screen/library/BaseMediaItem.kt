package org.jellyfin.mobile.ui.screen.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jellyfin.apiclient.model.dto.BaseItemType
import org.jellyfin.apiclient.model.entities.ImageType
import org.jellyfin.mobile.R
import org.jellyfin.mobile.model.dto.BaseMediaInfo
import org.jellyfin.mobile.ui.DefaultCornerRounding
import org.jellyfin.mobile.ui.utils.ApiImage

@Stable
@Composable
fun BaseMediaItem(
    info: BaseMediaInfo,
    modifier: Modifier = Modifier,
    imageDecorator: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(DefaultCornerRounding)
            .clickable(onClick = onClick)
            .padding(8.dp),
    ) {
        BoxWithConstraints {
            val imageSize = with(LocalDensity.current) { constraints.maxWidth.toDp() }
            ApiImage(
                id = info.id,
                modifier = Modifier.size(imageSize).clip(DefaultCornerRounding),
                imageType = ImageType.Primary,
                imageTag = info.primaryImageTag,
                fallback = {
                    Image(
                        painter = painterResource(
                            when (info.itemType) {
                                BaseItemType.MusicAlbum -> R.drawable.fallback_image_album_cover
                                BaseItemType.MusicArtist -> R.drawable.fallback_image_person
                                else -> 0
                            }
                        ),
                        contentDescription = null,
                    )
                },
            )
            imageDecorator()
        }
        Text(
            text = info.title,
            modifier = Modifier.padding(top = 6.dp, bottom = if (info.subtitle != null) 2.dp else 0.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        info.subtitle?.let { subtitle ->
            Text(
                text = subtitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}
