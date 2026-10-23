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
import com.example.week5reynaldo.soal2.model.allQuizQuestions
import com.example.week5reynaldo.soal2.viewmodel.QuizViewModel

@Composable
fun QuizStartScreen(quizViewModel: QuizViewModel = viewModel()) {
    val countInput by quizViewModel.questionCountInput.collectAsState()
    val error by quizViewModel.inputError.collectAsState()
    val errorText = error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 26.dp, horizontal = 14.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Mini Quiz",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "How many questions do you want? (1 - ${allQuizQuestions.size})",
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = countInput,
            onValueChange = quizViewModel::updateQuestionCount,
            label = { Text("Number of questions") },
            isError = errorText != null,
            supportingText = {
                if (errorText != null) {
                    Text(errorText, fontSize = 10.sp)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.Blue,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                errorCursorColor = Color.Red,
                errorSupportingTextColor = Color.Red
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { quizViewModel.startQuiz() },
            enabled = countInput.isNotEmpty() && error == null,
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
fun QuizStartScreenPreview() {
    QuizStartScreen()
}
