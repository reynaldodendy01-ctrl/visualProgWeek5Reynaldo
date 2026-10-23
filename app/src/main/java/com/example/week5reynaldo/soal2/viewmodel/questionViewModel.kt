package com.example.week5reynaldo.soal2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week5reynaldo.soal2.model.QuizQuestion
import com.example.week5reynaldo.soal2.model.allQuizQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel: ViewModel() {
    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val maxQuestions = allQuizQuestions.size

    private val _userAnswers = MutableStateFlow<List<String>>(emptyList())
    val userAnswers: StateFlow<List<String>> = _userAnswers.asStateFlow()

    private val _selectedOption = MutableStateFlow<String?>(null)
    val selectedOption: StateFlow<String?> = _selectedOption.asStateFlow()

    private val _questionCountInput = MutableStateFlow("")
    val questionCountInput: StateFlow<String> = _questionCountInput.asStateFlow()

    private val _inputError = MutableStateFlow<String?>(null)
    val inputError: StateFlow<String?> = _inputError.asStateFlow()

    private var targetCount = 0

    fun updateQuestionCount(newInput: String) {
        _questionCountInput.value = newInput.filter { it.isDigit() }
        _inputError.value = null
    }

    fun startQuiz() {
        val num = _questionCountInput.value.toIntOrNull()
        if (num == null || num <= 0) {
            _inputError.value = "You need at least 1 question!"
            return
        }
        if (num > maxQuestions) {
            _inputError.value = "Maximum is $maxQuestions questions."
            return
        }
        targetCount = num
        _quizQuestions.value = allQuizQuestions.shuffled().take(targetCount).map {
            it.copy(options = it.options.shuffled())
        }
        _userAnswers.value = emptyList()
        _selectedOption.value = null
    }

    fun selectOption(option: String) {
        _selectedOption.value = option
    }

    fun nextQuestion() {
        val chosen = _selectedOption.value
        if (chosen == null) {
            return
        }
        _userAnswers.update { it + chosen }
        _selectedOption.value = null
    }

    fun restartQuiz() {
        _quizQuestions.value = emptyList()
        _userAnswers.value = emptyList()
        _selectedOption.value = null
        _questionCountInput.value = ""
    }
}
