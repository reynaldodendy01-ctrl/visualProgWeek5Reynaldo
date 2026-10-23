package com.example.week5reynaldo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week5reynaldo.soal1.ui.screen.ExpenseScreen
import com.example.week5reynaldo.soal2.ui.screen.QuizPageScreen
import com.example.week5reynaldo.ui.theme.Week5ReynaldoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week5ReynaldoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Gunakan ExpenseScreen() untuk Soal 1, atau QuizPageScreen() untuk Soal 2
                    ExpenseScreen()
                    // QuizPageScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoalPreview() {
    Week5ReynaldoTheme {
        ExpenseScreen()
    }
}
