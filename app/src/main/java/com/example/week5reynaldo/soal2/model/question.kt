package com.example.week5reynaldo.soal2.model

data class QuizQuestion(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: String
)

val allQuizQuestions = listOf(
    QuizQuestion("Natal dirayakan pada bulan?", listOf("Agustus", "November", "Desember", "Mei"), "Desember"),
    QuizQuestion("Ada berapa hari dalam seminggu?", listOf("11", "7", "9", "6"), "7"),
    QuizQuestion("Ibu Kota Di Indonesia?", listOf("Surabaya", "Surakarta", "Jakarta", "Medan"), "Jakarta"),
    QuizQuestion("Mata Kuliah VP pakai apa?", listOf("Word", "Android Studio", "Excel", "Photoshop"), "Android Studio"),
    QuizQuestion("Apakah Mata Kuliah VP susah?", listOf("Sangat Sulit", "Sangat Mudah", "Biasa", "Tidak Tahu"), "Sangat Mudah")
)
