package com.leoapps.findout.add_question.navigation.model

sealed interface AddQuestionNavCommand {
    object GoBack : AddQuestionNavCommand
}