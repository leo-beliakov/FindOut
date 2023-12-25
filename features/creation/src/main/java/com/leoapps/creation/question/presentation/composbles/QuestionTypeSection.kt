package com.leoapps.creation.question.presentation.composbles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoapps.creation.question.presentation.model.QuestionTypeUiModel
import com.leoapps.design_system.components.picker.OptionsPicker
import com.leoapps.design_system.components.picker.model.Option

internal fun LazyListScope.questionTypeSection(
    selectedType: QuestionTypeUiModel,
    availableTypes: List<QuestionTypeUiModel>,
    onTypeSelected: (QuestionTypeUiModel) -> Unit
) {
    item(key = "QuestionTypePicker") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OptionsPicker(
                selectedOption = Option(
                    id = selectedType.textResId,
                    text = stringResource(id = selectedType.id)
                ),
                options = availableTypes.map {
                    Option(
                        id = it.textResId,
                        text = stringResource(id = it.textResId)
                    )
                },
                onOptionSelected = { option ->
                    onTypeSelected(availableTypes.first { it.id == option.id })
                },
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}