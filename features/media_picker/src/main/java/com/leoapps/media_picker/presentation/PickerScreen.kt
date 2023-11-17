package com.leoapps.media_picker.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.leoapps.media_picker.navigation.PickerNavGraph
import com.leoapps.media_picker.presentation.model.PickerUiState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@PickerNavGraph(start = true)
@Destination(
    style = DestinationStyleBottomSheet::class,
    navArgsDelegate = PickerArgs::class
)
@Composable
fun PickerScreen(
    viewModel: PickerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val gridState = rememberLazyGridState()

//    sheetState.partialExpand()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = gridState,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(
            top = 6.dp,
            start = 6.dp,
            end = 6.dp,
            bottom = WindowInsets
                .navigationBars
                .asPaddingValues()
                .calculateBottomPadding() + 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        items(
            items = state.mediaItems,
            key = { item -> item.hashCode() } // todo check if it works
        ) { item ->
            Image(
                painter = rememberAsyncImagePainter(item.uri),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
//                ImageItem(
//                    item = item,
//                    isSelected = false //item.isSelected,
//                )
        }
    }
}

@Composable
fun ImageItem(
    item: PickerUiState.Photo,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier.aspectRatio(1f),
    ) {
        AsyncImage(
            model = item.uri,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        SelectionIndicator(
            isSelected = isSelected,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
        )
    }
}

@Composable
private fun SelectionIndicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isSelected) {

    } else {
        Canvas(modifier = modifier.size(22.dp)) {
            drawCircle(
                color = Color.Black,
                style = Fill
            )
            drawCircle(
                color = Color.White,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}


