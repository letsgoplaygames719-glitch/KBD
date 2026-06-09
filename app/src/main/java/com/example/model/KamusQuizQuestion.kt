package com.example.model

data class KamusQuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val type: String // e.g., "Kosa Kata", "Nahu", "Sarf"
)
