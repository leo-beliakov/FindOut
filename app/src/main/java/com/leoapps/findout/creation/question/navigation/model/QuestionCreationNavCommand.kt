package com.leoapps.findout.creation.question.navigation.model

sealed interface QuestionCreationNavCommand {
    object GoBack : QuestionCreationNavCommand
}