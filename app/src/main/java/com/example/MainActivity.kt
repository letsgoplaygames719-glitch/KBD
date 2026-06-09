package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.Question
import com.example.ui.theme.*
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    val quizViewModel: QuizViewModel = viewModel()
    val currentScreen by quizViewModel.currentScreen.collectAsStateWithLifecycle()
    val isDiagnosticComplete by quizViewModel.isDiagnosticComplete.collectAsStateWithLifecycle()
    val appNotification by quizViewModel.appNotification.collectAsStateWithLifecycle()

    val bgGradient = Brush.verticalGradient(
        colors = listOf(SleekCream, Color(0xFFF7F4EC), SleekCream)
    )

    if (!isDiagnosticComplete) {
        OnboardingDiagnosticScreen(viewModel = quizViewModel)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = bgGradient)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Shared push notification simulated banner
                AnimatedVisibility(
                    visible = appNotification != null,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    appNotification?.let { msg ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .border(1.5.dp, DiniGreenLight, RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text(text = "🔔", fontSize = 20.sp)
                                    Text(
                                        text = msg,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color.Black.copy(alpha = 0.05f), CircleShape)
                                        .clickable { quizViewModel.dismissNotification() }
                                        .padding(6.dp)
                                ) {
                                    Text(text = "❌", fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }

                // Screen area (scrollable / styled independently)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    when (currentScreen) {
                        Screen.HOME -> HomeScreen(viewModel = quizViewModel)
                        Screen.QUIZ -> QuizScreen(viewModel = quizViewModel)
                        Screen.RESULTS -> ResultsScreen(viewModel = quizViewModel)
                        Screen.STATS -> StatsScreen(viewModel = quizViewModel)
                        Screen.SETTINGS -> SettingsScreen(viewModel = quizViewModel)
                        Screen.KAMUS -> KamusScreen(viewModel = quizViewModel)
                    }
                }

                // Bottom Navigation (Material 3 bar styled per design instructions)
                BottomNavBar(
                    currentScreen = currentScreen,
                    onNavigate = { screen -> quizViewModel.navigateTo(screen) }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: QuizViewModel) {
    val scoreInsaniah by viewModel.highScoreInsaniah.collectAsStateWithLifecycle()
    val scoreIntelek by viewModel.highScoreIntelek.collectAsStateWithLifecycle()
    val scoreKbd by viewModel.highScoreKbd.collectAsStateWithLifecycle()
    val totalAttempts by viewModel.totalAttempts.collectAsStateWithLifecycle()

    // Calculate dynamic stats
    val avgScore = if (totalAttempts > 0) {
        val count = (if (scoreInsaniah > 0) 1 else 0) + (if (scoreIntelek > 0) 1 else 0) + (if (scoreKbd > 0) 1 else 0)
        if (count > 0) (scoreInsaniah + scoreIntelek + scoreKbd) / count else 0
    } else {
        0
    }

    val progressValue = if (totalAttempts > 0) {
        // Mock progress fraction up to 1.0f based on attempts
        (totalAttempts * 0.25f).coerceAtMost(1.0f)
    } else {
        0.05f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header Section with Maroon background and large rounded bottom corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SleekMaroon,
                    shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                )
                .statusBarsPadding()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "AHLAN WA SAHLAN",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    val userForm by viewModel.userForm.collectAsStateWithLifecycle()
                    val scoreStreak by viewModel.streakCount.collectAsStateWithLifecycle()

                    Text(
                        text = if (userForm != null) "Calon $userForm" else "Calon PKSK Dini",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    
                    // Streak horizontal fire tag
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "🔥", fontSize = 12.sp)
                        Text(
                            text = "$scoreStreak Hari Streak",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                // Profile avatar box
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "👤", fontSize = 22.sp)
                }
            }
        }

        // Floating Stats Card overlapping the header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = (-28).dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF800000).copy(alpha = 0.1f), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Column: High Score Average
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = "SKOR TERBAIK",
                            fontSize = 10.sp,
                            color = TextSlate500,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = if (avgScore > 0) "$avgScore%" else "Tiada",
                                fontSize = 22.sp,
                                color = SleekMaroon,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Purata",
                                fontSize = 10.sp,
                                color = TextSlate400,
                                modifier = Modifier.padding(bottom = 3.dp)
                            )
                        }
                    }

                    // Divider line
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(40.dp)
                            .background(BorderSlate100)
                    )

                    // Right Column: Active Exercise progress bar
                    Column(
                        modifier = Modifier
                            .weight(1.2f)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "STATUS LATIHAN",
                            fontSize = 10.sp,
                            color = TextSlate500,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Progress bar
                            LinearProgressIndicator(
                                progress = { progressValue },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(6.dp)
                                    .clip(CircleShape),
                                color = ProgressGreen,
                                trackColor = BorderSlate100
                            )
                            Text(
                                text = "Aktif",
                                fontSize = 11.sp,
                                color = TextSlate800,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Main Modules Header
        Text(
            text = "PILIH MODUL LATIHAN",
            fontSize = 11.sp,
            color = TextSlate400,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 12.dp)
        )

        // Row of Bahagian A & B cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bahagian A: Kecerdasan Insaniah
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .border(1.dp, BorderSlate100, RoundedCornerShape(24.dp))
                    .clickable { viewModel.startQuiz("KECERDASAN_INSANIAH") }
                    .testTag("module_insaniah_button"),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFF800000).copy(alpha = 0.08f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🧠", fontSize = 18.sp)
                    }

                    Column {
                        Text(
                            text = "Bahagian A",
                            fontSize = 10.sp,
                            color = SleekMaroon,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Kecerdasan Insaniah",
                            fontSize = 14.sp,
                            color = TextSlate800,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp
                        )
                        Text(
                            text = "20% EQ & Sahsiah",
                            fontSize = 10.sp,
                            color = TextSlate400,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }

            // Bahagian B: Kecerdasan Intelek
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .border(1.dp, BorderSlate100, RoundedCornerShape(24.dp))
                    .clickable { viewModel.startQuiz("KECERDASAN_INTELEK") }
                    .testTag("module_intelek_button"),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFF800000).copy(alpha = 0.08f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🔬", fontSize = 18.sp)
                    }

                    Column {
                        Text(
                            text = "Bahagian B",
                            fontSize = 10.sp,
                            color = SleekMaroon,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Kecerdasan Intelek",
                            fontSize = 14.sp,
                            color = TextSlate800,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp
                        )
                        Text(
                            text = "70% IQ & Akademik",
                            fontSize = 10.sp,
                            color = TextSlate400,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Special Islamic KBD card with full width green gradient background
        // Equivalent Tailwind config:
        // class="w-full px-6 py-5 rounded-3xl bg-gradient-to-r from-emerald-800 to-green-600 border-2 border-emerald-900 shadow-md text-white transition-all transform hover:scale-105"
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                // Visual distinction: thick high-contrast border colored with #2E7D32 Emerald Green
                .border(2.5.dp, DiniGreenLight, RoundedCornerShape(24.dp))
                .clickable { viewModel.startQuiz("KBD") }
                .testTag("module_kbd_button"),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(DiniGreenDark, DiniGreenLight)
                        )
                    )
                    .padding(20.dp)
            ) {
                // Background mosque emoji decoration
                Text(
                    text = "🕌",
                    fontSize = 72.sp,
                    color = Color.White.copy(alpha = 0.15f),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 10.dp, y = 15.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .background(SoftYellow, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "MODUL KHAS",
                                fontSize = 9.sp,
                                color = DiniGreenDark,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Asas KBD",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Kurikulum Bersepadu Dini (SMAP/SBPI)",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Al-Syariah • Usul al-Din • Lughah",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "▶️", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // New Arabic-Malay Dictionary Card on Dashboard
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, DiniGreenLight.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .clickable { viewModel.navigateTo(Screen.KAMUS) }
                .testTag("dashboard_kamus_button"),
            colors = CardDefaults.cardColors(containerColor = SoftWhite),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(DiniGreenLight.copy(alpha = 0.1f), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📖", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Kamus Arab-Melayu",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DiniGreenDark
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .background(SoftYellow, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "KBD",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black,
                                color = DiniGreenDark
                            )
                        }
                    }
                    Text(
                        text = "Imbas & cari maksud kosa kata Dini",
                        fontSize = 12.sp,
                        color = TextSlate550,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(DiniGreenDark.copy(alpha = 0.08f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "▶️", color = DiniGreenDark, fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun QuizScreen(viewModel: QuizViewModel) {
    val activeCategory by viewModel.activeCategory.collectAsStateWithLifecycle()
    val questions by viewModel.activeQuestions.collectAsStateWithLifecycle()
    val currentIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
    val selectedOption by viewModel.selectedOptionIndex.collectAsStateWithLifecycle()
    val hasAnswered by viewModel.hasAnswered.collectAsStateWithLifecycle()
    val score by viewModel.score.collectAsStateWithLifecycle()
    
    val timeLeft by viewModel.timeLeftSeconds.collectAsStateWithLifecycle()
    val initialTimeLimit by viewModel.initialTimeLimit.collectAsStateWithLifecycle()
    val isExamMode by viewModel.isExamMode.collectAsStateWithLifecycle()

    val currentQuestion = questions.getOrNull(currentIndex)

    if (currentQuestion == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = SleekMaroon)
        }
        return
    }

    // Dynamic Module Header name
    val moduleName = when (activeCategory) {
        "KECERDASAN_INSANIAH" -> "Bahagian A: Kecerdasan Insaniah"
        "KECERDASAN_INTELEK" -> "Bahagian B: Kecerdasan Intelek"
        "KBD" -> "Asas KBD (Dini)"
        else -> "Latihan PKSK"
    }

    val moduleHeaderColor = if (activeCategory == "KBD") DiniGreenDark else SleekMaroon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Quiz Header bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateTo(Screen.HOME) },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.05f), CircleShape)
            ) {
                Text("⬅️", fontSize = 18.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = moduleName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = moduleHeaderColor
                )
                Text(
                    text = "Soalan ${currentIndex + 1} dari ${questions.size}",
                    fontSize = 11.sp,
                    color = TextSlate500
                )
            }

            // Quiz Timer display
            val timerColor = if (timeLeft < 15) Color.Red else moduleHeaderColor
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(timerColor.copy(alpha = 0.1f))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "⏱️", fontSize = 12.sp)
                    Text(
                        text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
                        fontSize = 13.sp,
                        color = timerColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Ticking linear timer progress
        val progressFraction = timeLeft.toFloat() / initialTimeLimit.toFloat()
        LinearProgressIndicator(
            progress = { progressFraction },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = if (timeLeft < 15) Color.Red else moduleHeaderColor,
            trackColor = Color.Black.copy(alpha = 0.05f)
        )

        // Question Details and Interactive Radio choices
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Category tag
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(moduleHeaderColor.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = currentQuestion.subCategory.uppercase(),
                        fontSize = 10.sp,
                        color = moduleHeaderColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (isExamMode) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.07f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "MODE PEPERIKSAAN",
                            fontSize = 10.sp,
                            color = TextSlate500,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Beautiful Question bubble
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = currentQuestion.text,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSlate800,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(20.dp),
                    textAlign = if (activeCategory == "KBD" && currentQuestion.subCategory == "Al-Lughah") TextAlign.Right else TextAlign.Left
                )
            }

            // Interactive Dynamic Glossary / Mini Translator helper for KBD sub-subjects
            if (activeCategory == "KBD") {
                val matchedWords = remember(currentQuestion) {
                    com.example.data.ArabicDictionary.words.filter { word ->
                        val textToScan = (currentQuestion.text + " " + currentQuestion.options.joinToString(" ") + " " + currentQuestion.explanation).lowercase()
                        // Strip Arabic vowels / diacritics to make string match resilient
                        val cleanWord = word.arabic.replace("[َُِّْٰ]".toRegex(), "").lowercase()
                        val cleanText = textToScan.replace("[َُِّْٰ]".toRegex(), "").lowercase()
                        (cleanText.contains(cleanWord) && cleanWord.length > 2) || 
                        textToScan.contains(word.arabic.lowercase()) ||
                        (word.rumi.isNotEmpty() && textToScan.contains(word.rumi.lowercase()))
                    }
                }

                if (matchedWords.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    var showGlossary by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.5.dp, DiniGreenLight.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1FDF9)),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { showGlossary = !showGlossary }
                                    .testTag("kbd_glossary_toggle"),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "📖", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Pembantu Kamus KBD (${matchedWords.size} Kalimah Dikesan)",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark
                                    )
                                }
                                Text(
                                    text = if (showGlossary) "▲ Sembunyi" else "▼ Lihat Maksud BM",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DiniGreenLight
                                )
                            }

                            if (showGlossary) {
                                Spacer(modifier = Modifier.height(10.dp))
                                matchedWords.forEach { word ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    text = word.arabic,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DiniGreenDark
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = "(${word.rumi})",
                                                    fontSize = 11.sp,
                                                    color = TextSlate500
                                                )
                                            }
                                            Text(
                                                text = word.malay,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextSlate800
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Contoh: ${word.exampleArabic} — Maksud: ${word.exampleMalay}",
                                            fontSize = 10.sp,
                                            color = TextSlate500,
                                            lineHeight = 14.sp
                                        )
                                    }
                                    if (word != matchedWords.last()) {
                                        androidx.compose.material3.HorizontalDivider(
                                            color = DiniGreenLight.copy(alpha = 0.15f),
                                            thickness = 0.8.dp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Multiple Choices A, B, C, D
            val alphabet = listOf("A", "B", "C", "D")
            currentQuestion.options.forEachIndexed { optIndex, choiceText ->
                val isSelected = selectedOption == optIndex
                val isCorrect = currentQuestion.correctOptionIndex == optIndex

                // Determine border and background styling based on state and whether it has been locked
                val containerColor: Color
                val borderOutlineColor: Color
                val badgeColor: Color
                val badgeTextColor: Color

                if (hasAnswered) {
                    when {
                        isCorrect -> {
                            containerColor = Color(0xFFE8F5E9) // soft green success
                            borderOutlineColor = Color(0xFF4CAF50)
                            badgeColor = Color(0xFF4CAF50)
                            badgeTextColor = Color.White
                        }
                        isSelected && !isCorrect -> {
                            containerColor = Color(0xFFFFEBEE) // soft red error
                            borderOutlineColor = Color(0xFFE53935)
                            badgeColor = Color(0xFFE53935)
                            badgeTextColor = Color.White
                        }
                        else -> {
                            containerColor = SoftWhite
                            borderOutlineColor = BorderSlate100
                            badgeColor = Color.Black.copy(alpha = 0.05f)
                            badgeTextColor = TextSlate500
                        }
                    }
                } else {
                    if (isSelected) {
                        containerColor = moduleHeaderColor.copy(alpha = 0.05f)
                        borderOutlineColor = moduleHeaderColor
                        badgeColor = moduleHeaderColor
                        badgeTextColor = Color.White
                    } else {
                        containerColor = SoftWhite
                        borderOutlineColor = BorderSlate100
                        badgeColor = Color.Black.copy(alpha = 0.05f)
                        badgeTextColor = TextSlate500
                    }
                }

                val borderWidth = if (isSelected || (hasAnswered && isCorrect)) 2.dp else 1.dp
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .border(borderWidth, borderOutlineColor, RoundedCornerShape(16.dp))
                        .clickable(enabled = !hasAnswered) { viewModel.selectOption(optIndex) }
                        .testTag("quiz_option_$optIndex"),
                    colors = CardDefaults.cardColors(containerColor = containerColor),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Badge letter (A, B, C, D)
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(badgeColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = alphabet[optIndex],
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = badgeTextColor
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = choiceText,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSlate800,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Explanatory Note inside Learning mode
            if (hasAnswered && !isExamMode) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black.copy(alpha = 0.05f), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAF2)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "💡", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Huraian & Jawapan Betul",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSlate800
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentQuestion.explanation,
                            fontSize = 12.sp,
                            color = TextSlate500,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Action Toolbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SoftWhite)
                .padding(16.dp)
        ) {
            if (!hasAnswered) {
                Button(
                    onClick = { viewModel.submitAnswer() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("submit_answer_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = moduleHeaderColor),
                    shape = RoundedCornerShape(12.dp),
                    enabled = selectedOption != null
                ) {
                    Text(
                        text = "Sahkan Jawapan",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            } else {
                Button(
                    onClick = { viewModel.advanceQuestion() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("next_question_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = moduleHeaderColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Seterusnya",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Helper elements deleted as standard inline dp bounds are preferred

@Composable
fun ResultsScreen(viewModel: QuizViewModel) {
    val score by viewModel.score.collectAsStateWithLifecycle()
    val questions by viewModel.activeQuestions.collectAsStateWithLifecycle()
    val activeCategory by viewModel.activeCategory.collectAsStateWithLifecycle()

    val totalCount = questions.size
    val scorePercentage = if (totalCount > 0) ((score.toFloat() / totalCount.toFloat()) * 100).toInt() else 0

    // Dini classification status
    val islamicStatus = when {
        scorePercentage >= 85 -> "MUMTAZ (Cemerlang)"
        scorePercentage >= 70 -> "JAYYID JIDDAN (Sangat Baik)"
        scorePercentage >= 55 -> "JAYYID (Baik)"
        scorePercentage >= 40 -> "MAQBUL (Lulus)"
        else -> "DHAIF (Perlu Ulang Kaji)"
    }

    val themeColor = if (activeCategory == "KBD") DiniGreenDark else SleekMaroon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Keputusan Latihan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = themeColor
        )
        Text(
            text = "Prestasi simulasi ujian offline anda",
            fontSize = 13.sp,
            color = TextSlate500,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Large Badge Circle Graph representing the Score
        Box(
            modifier = Modifier
                .size(160.dp)
                .background(themeColor.copy(alpha = 0.08f), CircleShape)
                .border(6.dp, themeColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$scorePercentage%",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Black,
                    color = themeColor
                )
                Text(
                    text = "$score / $totalCount Betul",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSlate800
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Performance status text
        Text(
            text = islamicStatus,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (scorePercentage >= 55) ProgressGreen else SleekMaroon,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Analysis Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = SoftWhite),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Rumusan Percubaan",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSlate800
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Jumlah Soalan", fontSize = 12.sp, color = TextSlate500)
                    Text(text = "$totalCount soalan", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextSlate800)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Jawapan Betul", fontSize = 12.sp, color = TextSlate500)
                    Text(text = "$score betul", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ProgressGreen)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Jawapan Salah/Kosong", fontSize = 12.sp, color = TextSlate500)
                    Text(text = "${totalCount - score} salah", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SleekMaroon)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Action Buttons: Seterusnya & Lagi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { viewModel.navigateTo(Screen.HOME) },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(1.5.dp, themeColor, RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = themeColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Skrin Utama", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { viewModel.startQuiz(activeCategory) },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("score_retry_button"),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Latih Lagi 🔄", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun StatsScreen(viewModel: QuizViewModel) {
    val scoreInsaniah by viewModel.highScoreInsaniah.collectAsStateWithLifecycle()
    val scoreIntelek by viewModel.highScoreIntelek.collectAsStateWithLifecycle()
    val scoreKbd by viewModel.highScoreKbd.collectAsStateWithLifecycle()
    val totalAttempts by viewModel.totalAttempts.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Prestasi Saya",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = SleekMaroon
        )
        Text(
            text = "Rekod pencapaian dan peratusan skor terbaik",
            fontSize = 13.sp,
            color = TextSlate500,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Key stats block: Attempts
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SleekMaroon.copy(alpha = 0.05f)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "JUMLAH PERCUBAAN", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SleekMaroon)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$totalAttempts Kali Kuiz", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextSlate800)
                }
                Text(text = "🎯", fontSize = 32.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Breakdowns / Progress charts
        Text(
            text = "Skor Tertinggi Mengikut Modul",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSlate800,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Progress breakdown 1
        ModuleStatItem(
            title = "Bahagian A: Kecerdasan Insaniah",
            percentage = scoreInsaniah,
            color = SleekMaroon,
            icon = "🧠"
        )

        // Progress breakdown 2
        ModuleStatItem(
            title = "Bahagian B: Kecerdasan Intelek",
            percentage = scoreIntelek,
            color = SleekMaroon,
            icon = "🔬"
        )

        // Progress breakdown 3
        ModuleStatItem(
            title = "Modul Khas: Asas KBD",
            percentage = scoreKbd,
            color = DiniGreenDark,
            icon = "🕌"
        )

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(24.dp))

        // Reset progress button
        OutlinedButton(
            onClick = { viewModel.resetStatistics() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color.Red.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Set Semula Semua Rekod", fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ModuleStatItem(title: String, percentage: Int, color: Color, icon: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .border(1.dp, BorderSlate100, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SoftWhite),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = icon, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSlate800
                    )
                }
                Text(
                    text = "$percentage%",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color = color
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { percentage.toFloat() / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = color,
                trackColor = BorderSlate100
            )
        }
    }
}

@Composable
fun SettingsScreen(viewModel: QuizViewModel) {
    val isExamMode by viewModel.isExamMode.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Tetapan Aplikasi",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = SleekMaroon
        )
        Text(
            text = "Pilih mod latihan dan pelajari syarat kemasukan",
            fontSize = 13.sp,
            color = TextSlate500,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Card containing toggle mode switch
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = SoftWhite),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Mode Peperiksaan",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextSlate800
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Matikan huraian jawapan segera & lompat soalan secara automatik selepas sela masa.",
                            fontSize = 11.sp,
                            color = TextSlate550,
                            lineHeight = 16.sp
                        )
                    }
                    Switch(
                        checked = isExamMode,
                        onCheckedChange = { viewModel.toggleExamMode(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = SleekMaroon,
                            uncheckedThumbColor = TextSlate400,
                            uncheckedTrackColor = BorderSlate100
                        ),
                        modifier = Modifier.testTag("exam_mode_switch")
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Information Section: Panduan Aliran Agama KBD (SMAP / SBPI)
        Text(
            text = "Panduan & Kemasukan Aliran Dini",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextSlate800,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = SoftWhite),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                InfoBulletinRow(
                    unicode = "🏫",
                    title = "Sekolah Sasaran",
                    value = "Sekolah Menengah Agama Persekutuan (SMAP) & Sekolah Berasrama Penuh Integrasi (SBPI) mewajibkan aliran Kurikulum Bersepadu Dini (KBD)."
                )
                Spacer(modifier = Modifier.height(14.dp))
                InfoBulletinRow(
                    unicode = "📖",
                    title = "Skop Peperiksaan KBD",
                    value = "Menguji istilah perbualan Bahasa Arab, asas tatabahasa (Lughah), rukun dan hukum (Al-Syariah) serta aqidah & sirah nabi (Usul al-Din)."
                )
                Spacer(modifier = Modifier.height(14.dp))
                InfoBulletinRow(
                    unicode = "⌛",
                    title = "Format Pemasa",
                    value = "Soalan KBD diberikan sela masa 1.5 minit (90 saat) setiap satu manakala soalan normal diberi 60 saat."
                )
                Spacer(modifier = Modifier.height(14.dp))
                InfoBulletinRow(
                    unicode = "📶",
                    title = "100% Offline Tanpa Log Masuk",
                    value = "Aplikasi ini memelihara privasi sepenuhnya dan berfungsi sepenuhnya luar talian tanpa pengisian pangkalan data luaran."
                )
            }
        }

        // Show current Diagnostic Profile dynamically here:
        val userForm by viewModel.userForm.collectAsStateWithLifecycle()
        val understandingLevel by viewModel.understandingLevel.collectAsStateWithLifecycle()
        val recommendation by viewModel.diagnosticRecommendation.collectAsStateWithLifecycle()

        if (userForm != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Profil Diagnostik Anda",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextSlate800,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DiniGreenLight.copy(alpha = 0.3f), RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "🏫 Aliran Dini: $userForm | Profil: Tahap $understandingLevel",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DiniGreenLight
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = recommendation,
                        fontSize = 12.sp,
                        color = TextSlate550,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.restartDiagnostics() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("retry_diagnostic_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = DiniGreenLight),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Ulang Analisis Kefahaman 🔄", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun KamusScreen(viewModel: QuizViewModel) {
    var activeSubTab by remember { mutableStateOf("vocab") } // "vocab", "grammar", "quiz"
    
    // --- State for Vocab (Kosa Kata) ---
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }
    var isFlashcardMode by remember { mutableStateOf(false) }

    // Dictionary filter engine based on category and text query
    val filteredWords = remember(searchQuery, selectedCategory) {
        com.example.data.ArabicDictionary.words.filter { word ->
            val matchesCategory = selectedCategory == "Semua" || word.category == selectedCategory
            
            // Normalize inputs to clean up Arabic vowel marks/diacritics for search matching
            val cleanQuery = searchQuery.replace("[َُِّْٰ]".toRegex(), "").lowercase().trim()
            val cleanArabic = word.arabic.replace("[َُِّْٰ]".toRegex(), "").lowercase()
            
            val matchesSearch = searchQuery.isEmpty() ||
                    cleanArabic.contains(cleanQuery) ||
                    word.arabic.lowercase().contains(searchQuery.lowercase().trim()) ||
                    word.malay.lowercase().contains(searchQuery.lowercase().trim()) ||
                    word.rumi.lowercase().contains(searchQuery.lowercase().trim())
                    
            matchesCategory && matchesSearch
        }
    }

    var currentFlashcardIdx by remember { mutableStateOf(0) }
    var isCardFlipped by remember { mutableStateOf(false) }

    // Reset layout indices when filters or queries update
    LaunchedEffect(filteredWords.size) {
        currentFlashcardIdx = 0
        isCardFlipped = false
    }

    // --- State for Grammar (Tatabahasa) ---
    var grammarSearchQuery by remember { mutableStateOf("") }
    var selectedGrammarCategory by remember { mutableStateOf("Semua") }
    
    val filteredGrammarTopics = remember(grammarSearchQuery, selectedGrammarCategory) {
        com.example.data.ArabicGrammar.topics.filter { topic ->
            val matchesCategory = selectedGrammarCategory == "Semua" || topic.category.contains(selectedGrammarCategory)
            
            val matchesSearch = grammarSearchQuery.isEmpty() ||
                    topic.title.lowercase().contains(grammarSearchQuery.lowercase().trim()) ||
                    topic.arabicTitle.lowercase().contains(grammarSearchQuery.lowercase().trim()) ||
                    topic.explanation.lowercase().contains(grammarSearchQuery.lowercase().trim())
                    
            matchesCategory && matchesSearch
        }
    }

    // --- State for Interactive Kamus & Grammar Quiz ---
    var quizQuestions by remember { mutableStateOf(emptyList<com.example.model.KamusQuizQuestion>()) }
    var currentQuizIdx by remember { mutableStateOf(0) }
    var selectedOptionIdx by remember { mutableStateOf<Int?>(null) }
    var isAnswerLocked by remember { mutableStateOf(false) }
    var localQuizScore by remember { mutableStateOf(0) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    // Whenever we open/reopen the quiz tab, load a fresh randomized selection containing vocabulary & grammar questions
    LaunchedEffect(activeSubTab) {
        if (activeSubTab == "quiz") {
            quizQuestions = com.example.data.KamusQuizData.questions.shuffled().take(10)
            currentQuizIdx = 0
            selectedOptionIdx = null
            isAnswerLocked = false
            localQuizScore = 0
            isQuizCompleted = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        // Aesthetic Title Header
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Kamus & Rujukan Arab 📖",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DiniGreenDark
            )
            Text(
                text = "Modul Kosa Kata Sabitah & Rujukan Tatabahasa KBD-PKSK",
                fontSize = 11.sp,
                color = TextSlate550,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Three-Way Elegant Sub-Tab Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F5F9), RoundedCornerShape(14.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val subTabs = listOf(
                "vocab" to "📖 Kosa Kata",
                "grammar" to "🖋️ Tatabahasa",
                "quiz" to "🎮 Game/Kuiz"
            )
            subTabs.forEach { (key, label) ->
                val isSelected = activeSubTab == key
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) DiniGreenDark else Color.Transparent)
                        .clickable { activeSubTab = key }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else TextSlate500
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ==================== SUB-TAB 1: KOSA KATA ====================
        if (activeSubTab == "vocab") {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isFlashcardMode) "Kedah Imbasan (Flashcard)" else "Senarai Kata Kunci",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSlate600
                )

                // Segment selector for list/flashcard under vocabulary tab
                Row(
                    modifier = Modifier
                        .background(Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (!isFlashcardMode) Color.White else Color.Transparent)
                            .clickable { isFlashcardMode = false; isCardFlipped = false }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Senarai",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (!isFlashcardMode) DiniGreenDark else TextSlate500
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isFlashcardMode) Color.White else Color.Transparent)
                            .clickable { isFlashcardMode = true; isCardFlipped = false }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Imbasan",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isFlashcardMode) DiniGreenDark else TextSlate500
                        )
                    }
                }
            }

            if (!isFlashcardMode) {
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("dict_search_field"),
                    placeholder = { Text("Cari perkataan Arab, Rumi atau BM...", fontSize = 13.sp, color = TextSlate400) },
                    leadingIcon = { Text("🔍", fontSize = 16.sp, modifier = Modifier.padding(start = 6.dp)) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Text("❌", fontSize = 10.sp)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DiniGreenDark,
                        unfocusedBorderColor = BorderSlate100,
                        focusedContainerColor = SoftWhite,
                        unfocusedContainerColor = SoftWhite
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Thematic Category Chips Container
                val categories = listOf("Semua", "Peralatan & Sekolah", "Pengangkutan", "Kehidupan Harian", "Al-Syariah (Ibadah)", "Usul al-Din (Aqidah)")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        val isSelected = selectedCategory == cat
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) DiniGreenLight else Color(0xFFF1F5F9))
                                .clickable { selectedCategory = cat }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else TextSlate600
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (filteredWords.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🔍", fontSize = 48.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Tiada kosa kata dikesan",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSlate600
                            )
                            Text(
                                text = "Cuba perkataan atau penapis kategori lain.",
                                fontSize = 11.sp,
                                color = TextSlate400,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredWords.size) { index ->
                            val word = filteredWords[index]
                            var isExpanded by remember { mutableStateOf(false) }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp))
                                    .clickable { isExpanded = !isExpanded }
                                    .testTag("dict_item_${word.id}"),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    text = word.arabic,
                                                    fontSize = 22.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DiniGreenDark
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .background(DiniGreenLight.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = word.category.substringBefore(" ("),
                                                        fontSize = 8.sp,
                                                        fontWeight = FontWeight.Black,
                                                        color = DiniGreenDark
                                                    )
                                                }
                                            }
                                            Text(
                                                text = "Sebut: ${word.rumi}",
                                                fontSize = 12.sp,
                                                color = TextSlate500,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }

                                        Column(horizontalAlignment = Alignment.End) {
                                            Text(
                                                text = word.malay,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextSlate800
                                            )
                                            Text(
                                                text = if (isExpanded) "▲ Tutup" else "▼ Lihat Contoh",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = DiniGreenLight,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }
                                    }

                                    if (isExpanded) {
                                        Spacer(modifier = Modifier.height(14.dp))
                                        androidx.compose.material3.HorizontalDivider(
                                            color = BorderSlate100,
                                            thickness = 1.dp
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color(0xFFF8FAF9), RoundedCornerShape(12.dp))
                                                .padding(12.dp)
                                        ) {
                                            Text(
                                                text = "Contoh Penggunaan:",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextSlate500
                                            )
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = word.exampleArabic,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = DiniGreenDark,
                                                textAlign = TextAlign.Right,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Terjemahan: ${word.exampleMalay}",
                                                fontSize = 12.sp,
                                                color = TextSlate800,
                                                lineHeight = 16.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // ==================== FLASHCARD MODE ====================
                if (filteredWords.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tiada kosa kata dijumpai bagi penapis ini.",
                            color = TextSlate400,
                            fontSize = 13.sp
                        )
                    }
                } else {
                    val currentWord = filteredWords[currentFlashcardIdx.coerceIn(0, filteredWords.size - 1)]

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "KAD ${currentFlashcardIdx + 1} DARIPADA ${filteredWords.size}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = TextSlate400,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Responsive Dynamic Flashcard Wrapper
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(290.dp)
                                .border(2.dp, if (isCardFlipped) DiniGreenLight.copy(alpha = 0.5f) else SleekMaroon.copy(alpha = 0.4f), RoundedCornerShape(24.dp))
                                .clickable { isCardFlipped = !isCardFlipped }
                                .testTag("flashcard_flip_area"),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isCardFlipped) Color(0xFFF1FDF9) else Color(0xFFFFFDF5)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                if (!isCardFlipped) {
                                    // --- FRONT SIDE (ARABIC FOCUS) ---
                                    Box(
                                        modifier = Modifier
                                            .background(SoftYellow, RoundedCornerShape(6.dp))
                                            .padding(horizontal = 10.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "Kosa Kata Arab",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Black,
                                            color = DiniGreenDark
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(28.dp))
                                    Text(
                                        text = currentWord.arabic,
                                        fontSize = 38.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(28.dp))
                                    Text(
                                        text = "Tap untuk pusing kad 🔄",
                                        fontSize = 11.sp,
                                        color = TextSlate400
                                    )
                                } else {
                                    // --- BACK SIDE (MALAY & EXAMPLE FOCUS) ---
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFFD1FAE5), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 10.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "Maksud Bahasa Melayu",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Black,
                                            color = DiniGreenDark
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = currentWord.malay,
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextSlate800,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Sebut: ${currentWord.rumi}",
                                        fontSize = 14.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = TextSlate500,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    androidx.compose.material3.HorizontalDivider(
                                        color = DiniGreenLight.copy(alpha = 0.2f),
                                        thickness = 1.dp
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = currentWord.exampleArabic,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Cth Maksud: ${currentWord.exampleMalay}",
                                        fontSize = 11.sp,
                                        color = TextSlate600,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Flashcard Controller Panel
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    isCardFlipped = false
                                    if (currentFlashcardIdx > 0) {
                                        currentFlashcardIdx--
                                    } else {
                                        currentFlashcardIdx = filteredWords.size - 1
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE2E8F0)),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f).padding(end = 8.dp)
                            ) {
                                Text(text = "◀️ Undur", color = TextSlate700, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            Button(
                                onClick = { isCardFlipped = !isCardFlipped },
                                colors = ButtonDefaults.buttonColors(containerColor = SoftYellow),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.padding(horizontal = 4.dp)
                            ) {
                                Text(text = "🔄 Pusing", color = SleekMaroon, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            Button(
                                onClick = {
                                    isCardFlipped = false
                                    if (currentFlashcardIdx < filteredWords.size - 1) {
                                        currentFlashcardIdx++
                                    } else {
                                        currentFlashcardIdx = 0
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = DiniGreenDark),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f).padding(start = 8.dp)
                            ) {
                                Text(text = "Maju ▶️", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        // ==================== SUB-TAB 2: TATABAHASA (GRAMMAR) ====================
        else if (activeSubTab == "grammar") {
            // Search field
            OutlinedTextField(
                value = grammarSearchQuery,
                onValueChange = { grammarSearchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("grammar_search_field"),
                placeholder = { Text("Cari topik nahu, sarf atau BM...", fontSize = 13.sp, color = TextSlate400) },
                leadingIcon = { Text("🖋️", fontSize = 16.sp, modifier = Modifier.padding(start = 6.dp)) },
                trailingIcon = {
                    if (grammarSearchQuery.isNotEmpty()) {
                        IconButton(onClick = { grammarSearchQuery = "" }) {
                            Text("❌", fontSize = 10.sp)
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DiniGreenDark,
                    unfocusedBorderColor = BorderSlate100,
                    focusedContainerColor = SoftWhite,
                    unfocusedContainerColor = SoftWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Categories Selector
            val grammarCats = listOf("Semua", "Nahu", "Sarf")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                grammarCats.forEach { cat ->
                    val isSelected = selectedGrammarCategory == cat
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) DiniGreenLight else Color(0xFFF1F5F9))
                            .clickable { selectedGrammarCategory = cat }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = cat,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else TextSlate600
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredGrammarTopics.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📚", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Tiada topik tatabahasa ditemui",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextSlate600
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(filteredGrammarTopics.size) { index ->
                        val topic = filteredGrammarTopics[index]
                        var isExpanded by remember { mutableStateOf(false) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp))
                                .clickable { isExpanded = !isExpanded }
                                .testTag("grammar_topic_${topic.id}"),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = SoftWhite),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = topic.arabicTitle,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = DiniGreenDark
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = topic.title,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextSlate800
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Box(
                                                modifier = Modifier
                                                    .background(DiniGreenLight.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                            ) {
                                                Text(
                                                    text = topic.category,
                                                    fontSize = 8.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DiniGreenDark
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = if (isExpanded) "▲ Tutup" else "▼ Hukum",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenLight
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = topic.explanation,
                                    fontSize = 12.sp,
                                    color = TextSlate600,
                                    lineHeight = 16.sp
                                )

                                if (isExpanded) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    HorizontalDivider(color = BorderSlate100, thickness = 1.dp)
                                    Spacer(modifier = Modifier.height(10.dp))

                                    // Rule Highlights
                                    Text(
                                        text = "Kaedah & Hukum Ringkas:",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = topic.ruleSummary,
                                        fontSize = 11.sp,
                                        color = TextSlate700,
                                        lineHeight = 16.sp
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Live interactive examples
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFFF1FDF9), RoundedCornerShape(12.dp))
                                            .padding(12.dp)
                                    ) {
                                        Text(
                                            text = "Contoh & Analisis Sentaksis:",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = TextSlate500
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))

                                        topic.examples.forEach { item ->
                                            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                                Text(
                                                    text = item.arabicText,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DiniGreenDark,
                                                    textAlign = TextAlign.Right,
                                                    modifier = Modifier.fillMaxWidth()
                                                )
                                                Text(
                                                    text = "💡 Maksud/Huraian: " + item.explanation,
                                                    fontSize = 11.sp,
                                                    color = TextSlate700,
                                                    lineHeight = 15.sp
                                                )
                                            }
                                            if (item != topic.examples.last()) {
                                                HorizontalDivider(
                                                    color = DiniGreenLight.copy(alpha = 0.1f),
                                                    thickness = 0.8.dp,
                                                    modifier = Modifier.padding(vertical = 6.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ==================== SUB-TAB 3: KUIZ KAMUS & GRAMMAR ====================
        else if (activeSubTab == "quiz") {
            if (quizQuestions.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = DiniGreenDark)
                }
            } else if (isQuizCompleted) {
                // Quiz completed scoreboard layout
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "💎 KEPUTUSAN KUIZ KAMUS 💎",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        color = DiniGreenDark,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Circular Score Widget
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .background(DiniGreenLight.copy(alpha = 0.1f), CircleShape)
                            .border(4.dp, DiniGreenLight, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$localQuizScore",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = DiniGreenDark
                            )
                            HorizontalDivider(
                                color = DiniGreenLight.copy(alpha = 0.3f),
                                thickness = 2.dp,
                                modifier = Modifier.width(48.dp).padding(vertical = 4.dp)
                            )
                            Text(
                                text = "10 SOALAN",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSlate500
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Feedback Card
                    val scorePercent = localQuizScore * 10
                    val (titleBadge, descText, sticker) = when {
                        scorePercent >= 80 -> Triple("MUMTAZ (Cemerlang) ✨", "Kosa kata dan tatabahasa Arab anda sangat mantap! Anda berpotensi besar skor A dalam PKSK Dini.", "🌟")
                        scorePercent >= 50 -> Triple("JAYYID (Baik) 👍", "Pecahan jawapan anda baik, teruskan rajin merujuk Kamus & Tatabahasa untuk membiasakan istilah.", "📚")
                        else -> Triple("MAQBUL (Bina Usaha) 📖", "Masih banyak kosa kata dan kaedah nahu perlu dikuasai. Jangan putus asa, kuiz kamus boleh diulangi sedia kala!", "💪")
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = sticker, fontSize = 28.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = titleBadge,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DiniGreenDark
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = descText,
                                fontSize = 12.sp,
                                color = TextSlate600,
                                textAlign = TextAlign.Center,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {
                            // Reset state & reshuffle
                            quizQuestions = com.example.data.KamusQuizData.questions.shuffled().take(10)
                            currentQuizIdx = 0
                            selectedOptionIdx = null
                            isAnswerLocked = false
                            localQuizScore = 0
                            isQuizCompleted = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = DiniGreenDark),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("kbd_retry_quiz_btn")
                    ) {
                        Text(text = "Ulang Latihan Kuiz 🔄", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            } else {
                // Quiz playing state
                val activeQ = quizQuestions[currentQuizIdx]

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Question Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "KUIZ KAMUS: SOALAN ${currentQuizIdx + 1} / 10",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = TextSlate500,
                            letterSpacing = 0.5.sp
                        )

                        Box(
                            modifier = Modifier
                                .background(DiniGreenLight.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = activeQ.type,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = DiniGreenDark
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Progress bar
                    LinearProgressIndicator(
                        progress = { (currentQuizIdx + 1) / 10f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = DiniGreenLight,
                        trackColor = BorderSlate100,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Question Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Text(
                            text = activeQ.question,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextSlate800,
                            modifier = Modifier.padding(20.dp),
                            lineHeight = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Dynamic Multi Choice Selection Cards
                    activeQ.options.forEachIndexed { optIdx, optionText ->
                        val isSelected = selectedOptionIdx == optIdx
                        
                        // Compute card outline and container highlights when answer is locked
                        val containerBg = when {
                            isAnswerLocked && optIdx == activeQ.correctOptionIndex -> Color(0xFFF1FDF9) // Always highlight correct green
                            isAnswerLocked && isSelected && optIdx != activeQ.correctOptionIndex -> Color(0xFFFFF5F5) // Fail red
                            isSelected -> DiniGreenLight.copy(alpha = 0.05f)
                            else -> SoftWhite
                        }

                        val outlineColor = when {
                            isAnswerLocked && optIdx == activeQ.correctOptionIndex -> DiniGreenLight
                            isAnswerLocked && isSelected && optIdx != activeQ.correctOptionIndex -> Color.Red
                            isSelected -> DiniGreenLight
                            else -> BorderSlate100
                        }

                        val bulletColor = when {
                            isAnswerLocked && optIdx == activeQ.correctOptionIndex -> DiniGreenDark
                            isAnswerLocked && isSelected && optIdx != activeQ.correctOptionIndex -> Color.Red
                            isSelected -> DiniGreenLight
                            else -> TextSlate500
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .border(1.5.dp, outlineColor, RoundedCornerShape(16.dp))
                                .clickable {
                                    if (!isAnswerLocked) {
                                        selectedOptionIdx = optIdx
                                    }
                                }
                                .testTag("quiz_opt_$optIdx"),
                            colors = CardDefaults.cardColors(containerColor = containerBg),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(bulletColor.copy(alpha = 0.1f), CircleShape)
                                        .border(1.5.dp, bulletColor, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = (optIdx + 65).toChar().toString(), // A, B, C, D
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = bulletColor
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = optionText,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextSlate800,
                                    modifier = Modifier.weight(1f)
                                )

                                // Checkmark / Cross icon indicator when locked
                                if (isAnswerLocked) {
                                    if (optIdx == activeQ.correctOptionIndex) {
                                        Text(text = "🟢", fontSize = 14.sp)
                                    } else if (isSelected) {
                                        Text(text = "🔴", fontSize = 14.sp)
                                    }
                                }
                            }
                        }
                    }

                    // Explanation Box Shows up when answer is locked!
                    if (isAnswerLocked) {
                        Spacer(modifier = Modifier.height(18.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.2.dp, DiniGreenLight.copy(alpha = 0.25f), RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1FDF9)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "🎓", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Ulasan Pendidikan Arab",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DiniGreenDark
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = activeQ.explanation,
                                    fontSize = 11.sp,
                                    color = TextSlate700,
                                    lineHeight = 15.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Interactive Action Button
                    if (!isAnswerLocked) {
                        Button(
                            onClick = {
                                if (selectedOptionIdx != null) {
                                    isAnswerLocked = true
                                    if (selectedOptionIdx == activeQ.correctOptionIndex) {
                                        localQuizScore += 1
                                    }
                                }
                            },
                            enabled = selectedOptionIdx != null,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DiniGreenDark,
                                disabledContainerColor = Color(0xFFCBD5E1)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("quiz_lock_btn")
                        ) {
                            Text(
                                text = "Kunci Jawapan 🔒",
                                color = if (selectedOptionIdx != null) Color.White else TextSlate500,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Button(
                            onClick = {
                                if (currentQuizIdx < 9) {
                                    currentQuizIdx += 1
                                    selectedOptionIdx = null
                                    isAnswerLocked = false
                                } else {
                                    isQuizCompleted = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = DiniGreenDark),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("quiz_next_btn")
                        ) {
                            Text(
                                text = if (currentQuizIdx < 9) "Seterusnya ▶️" else "Selesai & Lihat Skor 🎉",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

// custom Color mapper for SubText to avoid type issues
private val TextSlate550 = Color(0xFF5D6B7C)
private val TextSlate600 = Color(0xFF475569)
private val TextSlate700 = Color(0xFF334155)

@Composable
fun InfoBulletinRow(unicode: String, title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(SleekMaroon.copy(alpha = 0.05f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = unicode, fontSize = 16.sp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextSlate800
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 11.sp,
                color = TextSlate500,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun BottomNavBar(currentScreen: Screen, onNavigate: (Screen) -> Unit) {
    // Custom Navigation Bar to perfectly map the design HTML dimensions and weights
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SoftWhite)
            .border(1.dp, BorderSlate100)
            .navigationBarsPadding() // Handled Bottom notch and system keys spacing cleanly
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab 1: Utama
            BottomNavTab(
                label = "Utama",
                icon = "🏠",
                selected = currentScreen == Screen.HOME || currentScreen == Screen.QUIZ || currentScreen == Screen.RESULTS,
                onClick = { onNavigate(Screen.HOME) },
                modifier = Modifier.testTag("nav_utama_tab")
            )

            // Tab 2: Kamus
            BottomNavTab(
                label = "Kamus/Subjek",
                icon = "📖",
                selected = currentScreen == Screen.KAMUS,
                onClick = { onNavigate(Screen.KAMUS) },
                modifier = Modifier.testTag("nav_kamus_tab")
            )

            // Tab 3: Prestasi
            BottomNavTab(
                label = "Prestasi",
                icon = "📊",
                selected = currentScreen == Screen.STATS,
                onClick = { onNavigate(Screen.STATS) },
                modifier = Modifier.testTag("nav_prestasi_tab")
            )

            // Tab 4: Tetapan
            BottomNavTab(
                label = "Tetapan",
                icon = "⚙️",
                selected = currentScreen == Screen.SETTINGS,
                onClick = { onNavigate(Screen.SETTINGS) },
                modifier = Modifier.testTag("nav_tetapan_tab")
            )
        }
    }
}

@Composable
fun BottomNavTab(
    label: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Active indicator pill
        Box(
            modifier = Modifier
                .background(
                    color = if (selected) SleekMaroon.copy(alpha = 0.1f) else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 24.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                color = if (selected) SleekMaroon else TextSlate400
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) SleekMaroon else TextSlate400
        )
    }
}

@Composable
fun OnboardingDiagnosticScreen(viewModel: QuizViewModel) {
    val step by viewModel.diagnosticStep.collectAsStateWithLifecycle()
    val userForm by viewModel.userForm.collectAsStateWithLifecycle()
    val understandingLevel by viewModel.understandingLevel.collectAsStateWithLifecycle()
    val recommendation by viewModel.diagnosticRecommendation.collectAsStateWithLifecycle()
    val diagnosticScore by viewModel.diagnosticScore.collectAsStateWithLifecycle()

    val bgGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF0FDF4), SleekCream, Color(0xFFF0FDF4))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = bgGradient)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        when (step) {
            0 -> {
                var selectedFormState by remember { mutableStateOf("Tingkatan 1") }
                var selectedLevelState by remember { mutableStateOf("Sederhana") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🕋 PRE-APP DIAGNOSTIK",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = DiniGreenLight,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Analisis Kefahaman KBD",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSlate800,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Sila lengkapkan profil ringkas anda untuk kami menganalisis kebolehan Asas KBD anda sebelum memasuki skrin utama.",
                        fontSize = 13.sp,
                        color = TextSlate500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // "Awak dari tingkatan berapa" Setup Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "💬 Awak dari tingkatan berapa?",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSlate800
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            val forms = listOf("Tingkatan 1", "Tingkatan 2", "Tingkatan 3", "Tingkatan 4", "Tingkatan 5")
                            forms.forEach { f ->
                                val active = selectedFormState == f
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedFormState = f }
                                        .padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = active,
                                        onClick = { selectedFormState = f },
                                        colors = RadioButtonDefaults.colors(selectedColor = DiniGreenLight)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = f,
                                        fontSize = 13.sp,
                                        fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                        color = if (active) DiniGreenLight else TextSlate800
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // "Berapa tahap kefahaman anda" Setup Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "📈 Berapa tahap kefahaman anda?",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSlate800
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            val levels = listOf("Lemah", "Sederhana", "Baik", "Sangat Baik")
                            levels.forEach { lvl ->
                                val active = selectedLevelState == lvl
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedLevelState = lvl }
                                        .padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = active,
                                        onClick = { selectedLevelState = lvl },
                                        colors = RadioButtonDefaults.colors(selectedColor = DiniGreenLight)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = lvl,
                                        fontSize = 13.sp,
                                        fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                                        color = if (active) DiniGreenLight else TextSlate800
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { viewModel.saveOnboardingForm(selectedFormState, selectedLevelState) },
                        modifier = Modifier.fillMaxWidth().height(50.dp).testTag("onboarding_next_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = DiniGreenLight),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Mulai Analisis Kefahaman ➡️", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
            1 -> {
                var quizIndex by remember { mutableStateOf(0) }
                val diagAnswers = remember { mutableStateListOf<Boolean>() }

                val diagQuestions = listOf(
                    Triple(
                        "Sifat Nafsiah bagi Allah SWT hanya mempunyai satu sifat sahaja iaitu...",
                        listOf("Al-Wujud (Ada)", "Al-Qidam (Sedia)", "Al-Baqa' (Kekal)", "Al-Wahdaniyah (Esa)"),
                        0
                    ),
                    Triple(
                        "Apakah maksud bagi ungkapan bahasa Arab: 'أَنَا أَذْهَبُ إِلَى المَدْرَسَةِ'?",
                        listOf("Saya pergi ke rumah", "Saya pergi ke sekolah", "Saya sedang bermain bola", "Saya sayang guru saya"),
                        1
                    ),
                    Triple(
                        "Antara berikut, yang manakah merupakan salah satu Rukun Wuduk yang wajib?",
                        listOf("Membaca Bismillah", "Membasuh telinga", "Niat dan membasuh muka", "Berkumur tiga kali"),
                        2
                    )
                )

                val currentQ = diagQuestions.getOrNull(quizIndex) ?: diagQuestions[0]

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⏱️ PENILAIAN SECARA LANGSUNG",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DiniGreenLight,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Soalan Diagnostik ${quizIndex + 1} / 3",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSlate800
                    )
                    Text(
                        text = "Analisis kefahaman sebelum buka app.",
                        fontSize = 12.sp,
                        color = TextSlate500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSlate100, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite)
                    ) {
                        Text(
                            text = currentQ.first,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSlate800,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    currentQ.second.forEachIndexed { optIdx, optStr ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .border(1.dp, BorderSlate100, RoundedCornerShape(16.dp))
                                .clickable {
                                    val isCorrect = optIdx == currentQ.third
                                    diagAnswers.add(isCorrect)
                                    if (quizIndex < 2) {
                                        quizIndex += 1
                                    } else {
                                        viewModel.submitDiagnosticQuiz(diagAnswers.toList())
                                    }
                                },
                            colors = CardDefaults.cardColors(containerColor = SoftWhite),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "${listOf("A","B","C","D")[optIdx]}. $optStr",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSlate800
                            )
                        }
                    }
                }
            }
            2 -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📈 KEPUTUSAN DIAGNOSTIK KBD",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = DiniGreenLight,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(DiniGreenLight.copy(alpha = 0.1f), CircleShape)
                            .border(4.dp, DiniGreenLight, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$diagnosticScore/3", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DiniGreenLight)
                            Text(text = "Betul", fontSize = 10.sp, color = TextSlate500)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, DiniGreenLight.copy(alpha = 0.2f), RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4FBF7))
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "📋", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Analisis Profil Anda", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DiniGreenLight)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Keadaan: Calon $userForm | Profil: Tahap $understandingLevel",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSlate800
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = recommendation,
                                fontSize = 12.sp,
                                color = TextSlate550,
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = { viewModel.navigateTo(Screen.HOME) },
                        modifier = Modifier.fillMaxWidth().height(50.dp).testTag("onboarding_finish_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = DiniGreenLight),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Buka Dashboard Utama 🚀", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}
