package com.example.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val category: String, // "KECERDASAN_INSANIAH", "KECERDASAN_INTELEK", "KBD"
    val subCategory: String // "Al-Syariah", "Usul al-Din", "Al-Lughah", "Sains", "Matematik", "Am", "EQ"
)
