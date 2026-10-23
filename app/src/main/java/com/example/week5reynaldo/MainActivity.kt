package com.example.week5reynaldo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week5reynaldo.soal1.ui.screen.expenseScreen
import com.example.week5reynaldo.ui.theme.Week5ReynaldoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week5ReynaldoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    expenseScreen( )
                    // Panggil screen dari soal1 di sini
                    //Soal1ExpenseScreen()

                    // Nanti kalau soal2 sudah dibuat, tinggal ditambah di bawahnya
                    // atau diatur sesuai kebutuhan (misal: Soal2Screen())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoalPreview() {
    Week5ReynaldoTheme {
        expenseScreen()
    }
}