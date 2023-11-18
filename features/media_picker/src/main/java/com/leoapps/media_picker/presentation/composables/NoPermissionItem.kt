package com.leoapps.media_picker.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.modifiers.clickableWithoutRipple
import com.leoapps.media_picker.R

@Composable
fun NoPermissionItem(
    @StringRes titleResId: Int,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .aspectRatio(ratio = 1f)
            .clickableWithoutRipple(onClick = onClick)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = null
        )
        Text(
            text = stringResource(id = titleResId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview
@Composable
fun NoPermissionItemPreview() {
    NoPermissionItem(
        titleResId = R.string.no_permission_item_camera,
        iconResId = R.drawable.ic_perm_media,
        onClick = {},
    )
}