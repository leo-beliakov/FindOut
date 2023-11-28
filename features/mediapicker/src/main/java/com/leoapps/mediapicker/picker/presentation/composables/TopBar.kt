package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leoapps.design_system.theme.Gray
import com.leoapps.design_system.theme.Purple40
import com.leoapps.design_system.theme.VioletLight
import com.leoapps.mediapicker.detail.presentation.composables.TOP_BAR_HEIGHT_DP
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    selectedAlbum: PickerUiState.Album?,
    albums: List<PickerUiState.Album>,
    onAlbumSelected: (PickerUiState.Album) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .height(TOP_BAR_HEIGHT_DP.dp)
            .padding(16.dp)
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        val rotation by animateFloatAsState(targetValue = if (isExpanded) 0f else 180f)

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
            modifier = modifier,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .menuAnchor()
                    .border(2.dp, VioletLight, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = selectedAlbum?.name ?: "",
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropUp,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation)
                )
            }
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                albums.forEach { album ->
                    DropdownMenuItem(
                        text = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = album.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (selectedAlbum == album) Purple40 else Color.Black
                                )
                                Text(
                                    text = album.size.toString(),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Gray
                                )
                            }
                        },
                        leadingIcon = {
                            AsyncImage(
                                model = album.coverImage.uri,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                filterQuality = FilterQuality.None,
                                modifier = Modifier.size(40.dp),
                            )
                        },
                        onClick = {
                            onAlbumSelected(album)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}