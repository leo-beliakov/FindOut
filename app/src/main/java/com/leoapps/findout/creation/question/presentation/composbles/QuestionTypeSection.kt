package com.leoapps.findout.creation.question.presentation.composbles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.components.picker.OptionsPicker
import com.leoapps.design_system.components.picker.model.Option
import com.leoapps.findout.creation.question.presentation.model.QuestionType

internal fun LazyListScope.questionTypeSection(
    selectedType: QuestionType,
    availableTypes: List<QuestionType>,
    onTypeSelected: (QuestionType) -> Unit
) {
    item(key = "QuestionTypePicker") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OptionsPicker(
                selectedOption = Option(
                    id = selectedType.nameResId,
                    text = stringResource(id = selectedType.nameResId)
                ),
                options = availableTypes.map {
                    Option(
                        id = it.nameResId,
                        text = stringResource(id = it.nameResId)
                    )
                },
                onOptionSelected = { option ->
                    onTypeSelected(availableTypes.first { it.nameResId == option.id })
                },
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}