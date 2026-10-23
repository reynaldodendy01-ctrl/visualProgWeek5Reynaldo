package com.example.week5reynaldo.soal2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5reynaldo.soal2.viewmodel.QuizViewModel

@Composable
fun QuizPageScreen(quizViewModel: QuizViewModel = viewModel()) {
    val questions by quizViewModel.quizQuestions.collectAsState()
    val answers by quizViewModel.userAnswers.collectAsState()

    if (questions.isEmpty()) {
        QuizStartScreen(quizViewModel = quizViewModel)
    } else if (answers.size >= questions.size) {
        QuizResultScreen(quizViewModel = quizViewModel)
    } else {
        QuizActiveScreen(quizViewModel = quizViewModel)
    }
}
