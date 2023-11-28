package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.components.button.PrimaryButton
import com.leoapps.mediapicker.R


@Composable
fun NoPermissionDisclaimer(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.no_permission_gallery_description),
            textAlign = TextAlign.Center,
        )
        PrimaryButton(
            text = stringResource(id = R.string.no_permission_gallery_button),
            onClick = onClick
        )
    }
}