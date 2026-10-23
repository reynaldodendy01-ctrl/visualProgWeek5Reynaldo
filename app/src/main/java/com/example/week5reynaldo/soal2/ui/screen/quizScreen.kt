package com.example.week5reynaldo.soal2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5reynaldo.soal2.viewmodel.QuizViewModel

@Composable
fun QuizActiveScreen(quizViewModel: QuizViewModel = viewModel()) {
    val questions by quizViewModel.quizQuestions.collectAsState()
    val answers by quizViewModel.userAnswers.collectAsState()
    val selected by quizViewModel.selectedOption.collectAsState()
    val currentIndex = answers.size
    val currentQuestion = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 26.dp, horizontal = 14.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Question ${currentIndex + 1}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            currentQuestion.questionText,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        currentQuestion.options.forEach { option ->
            Button(
                onClick = { quizViewModel.selectOption(option) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (option == selected) Color(0xFF4D5D8A) else Color(0xFFDCE2F9),
                    contentColor = if (option == selected) Color.White else Color.Black
                )
            ) {
                Text(option)
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = { quizViewModel.nextQuestion() },
            enabled = selected != null,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4D5D8A),
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray
            )
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizActiveScreenPreview() {
    QuizActiveScreen()
}
