package com.leoapps.findout.add_question.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoapps.findout.design_system.components.picker.OptionsPicker

fun LazyListScope.addQuestionTypeSection(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    item(key = "QuestionTypePicker") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OptionsPicker(
                selectedOption = selectedType,
                options = listOf("Single Answer", "Multiple Answer", "Open Answer"),
                onOptionSelected = { onTypeSelected(it) },
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}