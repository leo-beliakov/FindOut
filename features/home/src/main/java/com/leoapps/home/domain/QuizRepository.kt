package com.leoapps.home.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

interface QuizRepository {
    fun getAllQuizzesAsFlow(): Flow<List<Quiz>>
}

class QuizRepositoryImpl @Inject constructor(): QuizRepository {

    private val localCache = MutableStateFlow(
        listOf(
            Quiz(
                id = UUID.randomUUID(),
                name = "First quiz"
            ),
            Quiz(
                id = UUID.randomUUID(),
                name = "Second quiz"
            ),
            Quiz(
                id = UUID.randomUUID(),
                name = "Third quiz"
            )
        )
    )

    override fun getAllQuizzesAsFlow(): Flow<List<Quiz>> {
        return localCache.asStateFlow()
    }
}

data class Quiz(
    val id: UUID,
    val name: String
)