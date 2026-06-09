package com.example.model

data class GrammarTopic(
    val id: Int,
    val title: String, // e.g. "Fi'l Madi & Fi'l Mudari'"
    val arabicTitle: String, // e.g. "الفعل الماضي والمضارع"
    val category: String, // e.g. "Nahu (Sintaksis)" or "Sarf (Morfologi)"
    val explanation: String, // Description in Malay
    val ruleSummary: String, // Key rule
    val examples: List<GrammarExample>
)

data class GrammarExample(
    val arabicText: String,
    val explanation: String // translation or grammatical role explanation in Malay
)
