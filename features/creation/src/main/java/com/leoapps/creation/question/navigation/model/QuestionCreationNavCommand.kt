package com.leoapps.creation.question.navigation.model

sealed interface QuestionCreationNavCommand {
    object GoBack : QuestionCreationNavCommand
    object OpenImagePicker : QuestionCreationNavCommand
}