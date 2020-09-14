package org.jellyfin.mobile.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jellyfin.apiclient.model.entities.ImageType
import org.jellyfin.mobile.controller.LibraryController
import org.jellyfin.mobile.model.dto.UserViewInfo
import org.jellyfin.mobile.ui.DefaultCornerRounding
import org.jellyfin.mobile.ui.LocalBackStack
import org.jellyfin.mobile.ui.Routing
import org.jellyfin.mobile.ui.utils.ApiImage

@Composable
fun UserViews(libraryController: LibraryController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(1f),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
    ) {
        items(libraryController.userViews) { item ->
            UserView(item = item)
        }
    }
}

@Composable
fun UserView(item: UserViewInfo) {
    Column(modifier = Modifier.padding(4.dp)) {
        val backstack = LocalBackStack.current
        val width = 256.dp
        val height = 144.dp
        ApiImage(
            id = item.id,
            modifier = Modifier.width(width).height(height).clip(DefaultCornerRounding).clickable(onClick = {
                backstack.push(Routing.Library(item))
            }),
            imageType = ImageType.Primary,
            imageTag = item.primaryImageTag,
        )
        Text(
            text = item.name,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 8.dp),
        )
    }
}
