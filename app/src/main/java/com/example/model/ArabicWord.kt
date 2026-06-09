package com.example.model

data class ArabicWord(
    val id: Int,
    val arabic: String,
    val rumi: String,
    val malay: String,
    val category: String, // e.g. "Sekolah", "Kenderaan", "Harian", "Usul al-Din", "Al-Syariah"
    val exampleArabic: String,
    val exampleMalay: String
)
