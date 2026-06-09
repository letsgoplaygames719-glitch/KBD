package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.QuestionsData
import com.example.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class Screen {
    HOME, QUIZ, RESULTS, STATS, SETTINGS, KAMUS
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPrefs: SharedPreferences =
        application.getSharedPreferences("pksk_kbd_prefs", Context.MODE_PRIVATE)

    // Current screen
    private val _currentScreen = MutableStateFlow(Screen.HOME)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Navigation function
    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    // Active quiz state
    private val _activeCategory = MutableStateFlow("") // "KECERDASAN_INSANIAH", "KECERDASAN_INTELEK", "KBD"
    val activeCategory: StateFlow<String> = _activeCategory.asStateFlow()

    private val _activeQuestions = MutableStateFlow<List<Question>>(emptyList())
    val activeQuestions: StateFlow<List<Question>> = _activeQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedOptionIndex = MutableStateFlow<Int?>(null)
    val selectedOptionIndex: StateFlow<Int?> = _selectedOptionIndex.asStateFlow()

    private val _hasAnswered = MutableStateFlow(false)
    val hasAnswered: StateFlow<Boolean> = _hasAnswered.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    // Countdown Timer State
    private val _timeLeftSeconds = MutableStateFlow(0)
    val timeLeftSeconds: StateFlow<Int> = _timeLeftSeconds.asStateFlow()

    private val _initialTimeLimit = MutableStateFlow(60)
    val initialTimeLimit: StateFlow<Int> = _initialTimeLimit.asStateFlow()

    private var timerJob: Job? = null

    // High Scores (SharedPreferences)
    private val _highScoreInsaniah = MutableStateFlow(sharedPrefs.getInt("high_score_insaniah", 0))
    val highScoreInsaniah: StateFlow<Int> = _highScoreInsaniah.asStateFlow()

    private val _highScoreIntelek = MutableStateFlow(sharedPrefs.getInt("high_score_intelek", 0))
    val highScoreIntelek: StateFlow<Int> = _highScoreIntelek.asStateFlow()

    private val _highScoreKbd = MutableStateFlow(sharedPrefs.getInt("high_score_kbd", 0))
    val highScoreKbd: StateFlow<Int> = _highScoreKbd.asStateFlow()

    private val _totalAttempts = MutableStateFlow(sharedPrefs.getInt("total_attempts", 0))
    val totalAttempts: StateFlow<Int> = _totalAttempts.asStateFlow()

    // Streaks state
    private val _streakCount = MutableStateFlow(sharedPrefs.getInt("streak_count", 0))
    val streakCount: StateFlow<Int> = _streakCount.asStateFlow()

    // App Notification Banner State
    private val _appNotification = MutableStateFlow<String?>(null)
    val appNotification: StateFlow<String?> = _appNotification.asStateFlow()

    // Onboarding & Pre-App Diagnostics Values
    private val _userForm = MutableStateFlow(sharedPrefs.getString("user_form", null))
    val userForm: StateFlow<String?> = _userForm.asStateFlow()

    private val _understandingLevel = MutableStateFlow(sharedPrefs.getString("understanding_level", null))
    val understandingLevel: StateFlow<String?> = _understandingLevel.asStateFlow()

    private val _isDiagnosticComplete = MutableStateFlow(sharedPrefs.getBoolean("is_diagnostic_complete", false))
    val isDiagnosticComplete: StateFlow<Boolean> = _isDiagnosticComplete.asStateFlow()

    private val _diagnosticStep = MutableStateFlow(0) // 0: Onboarding Form, 1: Quiz, 2: Result
    val diagnosticStep: StateFlow<Int> = _diagnosticStep.asStateFlow()

    private val _diagnosticScore = MutableStateFlow(0)
    val diagnosticScore: StateFlow<Int> = _diagnosticScore.asStateFlow()

    private val _diagnosticRecommendation = MutableStateFlow(sharedPrefs.getString("diagnostic_recommendation", "") ?: "")
    val diagnosticRecommendation: StateFlow<String> = _diagnosticRecommendation.asStateFlow()

    // Learning vs Exam Mode (Default Learning Mode where they see immediate explanations)
    private val _isExamMode = MutableStateFlow(false)
    val isExamMode: StateFlow<Boolean> = _isExamMode.asStateFlow()

    init {
        checkAndTriggerDailyStreak()
        triggerDailyReminderNotification()
    }

    private fun checkAndTriggerDailyStreak() {
        val lastQuizTime = sharedPrefs.getLong("last_quiz_time", 0L)
        val today = System.currentTimeMillis()
        val oneDayMillis = 24 * 60 * 60 * 1000L
        
        var currentStreak = sharedPrefs.getInt("streak_count", 0)
        
        if (lastQuizTime == 0L) {
            // First time - Award 1 day streak as starting bonus!
            currentStreak = 1
            sharedPrefs.edit()
                .putInt("streak_count", 1)
                .putLong("last_quiz_time", today)
                .apply()
            _streakCount.value = 1
        } else {
            val diff = today - lastQuizTime
            if (diff >= oneDayMillis && diff <= oneDayMillis * 2) {
                // Consecutive day!
                currentStreak += 1
                sharedPrefs.edit()
                    .putInt("streak_count", currentStreak)
                    .putLong("last_quiz_time", today)
                    .apply()
                _streakCount.value = currentStreak
            } else if (diff > oneDayMillis * 2) {
                // Streak broken
                currentStreak = 1
                sharedPrefs.edit()
                    .putInt("streak_count", 1)
                    .putLong("last_quiz_time", today)
                    .apply()
                _streakCount.value = 1
            }
        }
    }

    private fun triggerDailyReminderNotification() {
        viewModelScope.launch {
            delay(1200L) // Show simulated notifications toast
            _appNotification.value = "🔔 Peringatan PKSK Dini: Jom kekalkan 🔥 ${_streakCount.value} Hari Streak anda dengan berlatih kuiz Asas KBD sekarang!"
        }
    }

    fun dismissNotification() {
        _appNotification.value = null
    }

    fun toggleExamMode(enabled: Boolean) {
        _isExamMode.value = enabled
    }

    // Diagnostic Setup
    fun saveOnboardingForm(form: String, level: String) {
        _userForm.value = form
        _understandingLevel.value = level
        _diagnosticStep.value = 1 // Go to Diagnostic Quiz
    }

    fun submitDiagnosticQuiz(answers: List<Boolean>) {
        val correctCount = answers.count { it }
        _diagnosticScore.value = correctCount

        val form = _userForm.value ?: "Tingkatan 1"
        val level = _understandingLevel.value ?: "Sederhana"

        val recommendation = when {
            correctCount >= 3 -> "Syor Mumtaz: Tahap kefahaman $level bagi $form adalah cemerlang! Anda sangat bersedia untuk menduduki aliran Dini di SMAP / SBPI. Teruskan mengekalkan prestasi."
            correctCount >= 1 -> "Syor Jayyid: Anda mempunyai pemahaman asas munasabah. Kami mencadangkan anda memperbanyakkan latihan Modul Khas Asas KBD (fokus Al-Syariah dan bahasa Arab Al-Lughah) untuk kecemerlangan."
            else -> "Syor Thaqafah: Tahap kecerdasan KBD memerlukan bimbingan tambahan. Cadangan: Mula ulangkaji bab Akidah Usul al-Din dan perbendaharaan kata asas setiap hari untuk bina keyakinan."
        }

        _diagnosticRecommendation.value = recommendation

        val editor = sharedPrefs.edit()
        editor.putString("user_form", form)
        editor.putString("understanding_level", level)
        editor.putString("diagnostic_recommendation", recommendation)
        editor.putBoolean("is_diagnostic_complete", true)
        editor.apply()

        _isDiagnosticComplete.value = true
        _diagnosticStep.value = 2 // Show report
    }

    fun restartDiagnostics() {
        _diagnosticStep.value = 0
        _isDiagnosticComplete.value = false
        sharedPrefs.edit().putBoolean("is_diagnostic_complete", false).apply()
    }

    // Start a new quiz for a module
    fun startQuiz(category: String) {
        _activeCategory.value = category
        _currentQuestionIndex.value = 0
        _selectedOptionIndex.value = null
        _hasAnswered.value = false
        _score.value = 0

        // Filter and shuffle to get max 10 questions for a fresh layout
        val filtered = QuestionsData.questions.filter { it.category == category }
        _activeQuestions.value = filtered.shuffled().take(10)

        // Reset timer
        val limit = if (category == "KBD") 90 else 60 // 1.5 mins for KBD, 1 min for others
        _initialTimeLimit.value = limit
        
        _currentScreen.value = Screen.QUIZ
        startNewQuestionTimer()
    }

    // Timer control
    private fun startNewQuestionTimer() {
        timerJob?.cancel()
        _timeLeftSeconds.value = _initialTimeLimit.value
        timerJob = viewModelScope.launch {
            while (_timeLeftSeconds.value > 0) {
                delay(1000L)
                _timeLeftSeconds.value -= 1
            }
            // Time ran out!
            if (!_hasAnswered.value) {
                timeOutQuestion()
            }
        }
    }

    private fun timeOutQuestion() {
        _selectedOptionIndex.value = -1 // Indication of no answer chosen
        _hasAnswered.value = true
        timerJob?.cancel()
        
        // In integrated exam mode, automatically advance after a short delay since there are no manual reviews
        if (_isExamMode.value) {
            viewModelScope.launch {
                delay(1500L)
                advanceQuestion()
            }
        }
    }

    // Select an option (A=0, B=1, C=2, D=3)
    fun selectOption(optionIndex: Int) {
        if (_hasAnswered.value) return // cannot change answer once locked
        _selectedOptionIndex.value = optionIndex
    }

    // Commit/Lock answer (useful for Learning Mode or submitting in Exam Mode)
    fun submitAnswer() {
        if (_hasAnswered.value || _selectedOptionIndex.value == null) return
        _hasAnswered.value = true
        timerJob?.cancel()

        val currentQ = _activeQuestions.value.getOrNull(_currentQuestionIndex.value)
        if (currentQ != null && _selectedOptionIndex.value == currentQ.correctOptionIndex) {
            _score.value += 1
        }

        // If in exam mode, move quickly after showing check
        if (_isExamMode.value) {
            viewModelScope.launch {
                delay(1000L)
                advanceQuestion()
            }
        }
    }

    // Advance to next question or screen
    fun advanceQuestion() {
        val nextIndex = _currentQuestionIndex.value + 1
        if (nextIndex < _activeQuestions.value.size) {
            _currentQuestionIndex.value = nextIndex
            _selectedOptionIndex.value = null
            _hasAnswered.value = false
            startNewQuestionTimer()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        timerJob?.cancel()
        val totalQCount = _activeQuestions.value.size
        if (totalQCount == 0) {
            _currentScreen.value = Screen.HOME
            return
        }

        val percentage = ((_score.value.toFloat() / totalQCount.toFloat()) * 100).toInt()
        
        // Save score if it is a new high score
        val editor = sharedPrefs.edit()
        
        when (_activeCategory.value) {
            "KECERDASAN_INSANIAH" -> {
                if (percentage > _highScoreInsaniah.value) {
                    _highScoreInsaniah.value = percentage
                    editor.putInt("high_score_insaniah", percentage)
                }
            }
            "KECERDASAN_INTELEK" -> {
                if (percentage > _highScoreIntelek.value) {
                    _highScoreIntelek.value = percentage
                    editor.putInt("high_score_intelek", percentage)
                }
            }
            "KBD" -> {
                if (percentage > _highScoreKbd.value) {
                    _highScoreKbd.value = percentage
                    editor.putInt("high_score_kbd", percentage)
                }
            }
        }

        val attempts = _totalAttempts.value + 1
        _totalAttempts.value = attempts
        editor.putInt("total_attempts", attempts)

        // Increment dynamic streak on quiz completion!
        val today = System.currentTimeMillis()
        editor.putLong("last_quiz_time", today)
        val currentStreak = _streakCount.value + 1
        _streakCount.value = currentStreak
        editor.putInt("streak_count", currentStreak)
        
        editor.apply()

        // Also trigger completion notification reward
        _appNotification.value = "🎉 Syabas! Anda berjaya menamatkan latihan. Streak anda meningkat ke 🔥 $currentStreak Hari!"

        _currentScreen.value = Screen.RESULTS
    }

    // Reset statistics
    fun resetStatistics() {
        val editor = sharedPrefs.edit()
        editor.putInt("high_score_insaniah", 0)
        editor.putInt("high_score_intelek", 0)
        editor.putInt("high_score_kbd", 0)
        editor.putInt("total_attempts", 0)
        editor.putInt("streak_count", 1) // preserve 1 day default
        editor.apply()

        _highScoreInsaniah.value = 0
        _highScoreIntelek.value = 0
        _highScoreKbd.value = 0
        _totalAttempts.value = 0
        _streakCount.value = 1
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
