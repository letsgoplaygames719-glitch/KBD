package com.example.data

import com.example.model.ArabicWord

object ArabicDictionary {
    val words = listOf(
        // === SEKOLAH / PERALATAN ===
        ArabicWord(
            id = 1,
            arabic = "المَدْرَسَة",
            rumi = "Al-Madrasah",
            malay = "Sekolah",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَذْهَبُ إِلَى المَدْرَسَةِ كُلَّ يَوْمٍ.",
            exampleMalay = "Saya pergi ke sekolah setiap hari."
        ),
        ArabicWord(
            id = 2,
            arabic = "المَكْتَبَة",
            rumi = "Al-Maktabah",
            malay = "Perpustakaan / Kedai Buku",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَقْرَأُ الكِتَابَ فِي المَكْتَبَةِ.",
            exampleMalay = "Saya membaca buku di perpustakaan."
        ),
        ArabicWord(
            id = 3,
            arabic = "المَكْتَب",
            rumi = "Al-Maktab",
            malay = "Meja / Pejabat",
            category = "Peralatan & Sekolah",
            exampleArabic = "القَلَمُ عَلَى المَكْتَبِ.",
            exampleMalay = "Pena itu di atas meja."
        ),
        ArabicWord(
            id = 4,
            arabic = "الكِتَاب",
            rumi = "Al-Kitab",
            malay = "Buku",
            category = "Peralatan & Sekolah",
            exampleArabic = "هَذَا كِتَابٌ جَدِيدٌ.",
            exampleMalay = "Ini buku baharu."
        ),
        ArabicWord(
            id = 5,
            arabic = "القَلَم",
            rumi = "Al-Qalam",
            malay = "Pena / Pen",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَكْتُبُ بِالقَلَمِ الأَزْرَقِ.",
            exampleMalay = "Saya menulis dengan pen biru."
        ),
        ArabicWord(
            id = 6,
            arabic = "مُعَلِّم",
            rumi = "Mu'allim",
            malay = "Guru / Pendidik",
            category = "Peralatan & Sekolah",
            exampleArabic = "المُعَلِّمُ يَشْرَحُ الدَّرْسَ بِوُضُوحٍ.",
            exampleMalay = "Guru itu menerangkan pelajaran dengan jelas."
        ),
        ArabicWord(
            id = 7,
            arabic = "طَالِب",
            rumi = "Thalib",
            malay = "Pelajar / Murid",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَنَا طَالِبٌ مُجْتَهِدٌ.",
            exampleMalay = "Saya seorang pelajar yang rajin."
        ),
        ArabicWord(
            id = 8,
            arabic = "المَلْعَب",
            rumi = "Al-Mal'ab",
            malay = "Padang Permainan / Stadium",
            category = "Peralatan & Sekolah",
            exampleArabic = "يَلْعَبُ الأَوْلَادُ فِي المَلْعَبِ.",
            exampleMalay = "Kanak-kanak bermain di padang."
        ),
        ArabicWord(
            id = 9,
            arabic = "حَقِيبَة",
            rumi = "Haqibah",
            malay = "Beg",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَضَعُ الكُتُبَ فِي الحَقِيبَةِ.",
            exampleMalay = "Saya meletakkan buku-buku di dalam beg."
        ),
        ArabicWord(
            id = 10,
            arabic = "مِسْطَرَة",
            rumi = "Mistarah",
            malay = "Pembaris",
            category = "Peralatan & Sekolah",
            exampleArabic = "أَرْسُمُ الخَطَّ بِالمِسْطَرَةِ.",
            exampleMalay = "Saya melukis garisan dengan pembaris."
        ),

        // === PENGANGKUTAN ===
        ArabicWord(
            id = 11,
            arabic = "الحَافِلَة",
            rumi = "Al-Hafilah",
            malay = "Bas",
            category = "Pengangkutan",
            exampleArabic = "رَكِبْتُ الحَافِلَةَ إِلَى المَدْرَسَةِ.",
            exampleMalay = "Saya naik bas ke sekolah."
        ),
        ArabicWord(
            id = 12,
            arabic = "السَّيَّارَة",
            rumi = "Al-Sayyarah",
            malay = "Kereta",
            category = "Pengangkutan",
            exampleArabic = "سَيَّارَةُ أَبِي لَوْنُهَا أَحْمَرُ.",
            exampleMalay = "Kereta ayah saya berwarna merah."
        ),
        ArabicWord(
            id = 13,
            arabic = "القِطَار",
            rumi = "Al-Qitar",
            malay = "Kereta Api",
            category = "Pengangkutan",
            exampleArabic = "سَافَرْنَا إِلَى كُوالالُمْبُور بِالقِطَارِ.",
            exampleMalay = "Kami melancong ke Kuala Lumpur dengan kereta api."
        ),
        ArabicWord(
            id = 14,
            arabic = "الدَّرَّاجَة",
            rumi = "Al-Darrajah",
            malay = "Basikal",
            category = "Pengangkutan",
            exampleArabic = "أَرْكَبُ الدَّرَّاجَةَ فِي المَسَاءِ.",
            exampleMalay = "Saya menunggang basikal pada waktu petang."
        ),
        ArabicWord(
            id = 15,
            arabic = "الطَّائِرَة",
            rumi = "At-Thairah",
            malay = "Kapal Terbang",
            category = "Pengangkutan",
            exampleArabic = "تَطِيرُ الطَّائِرَةُ فِي السَّمَاءِ.",
            exampleMalay = "Kapal terbang terbang di angkasa."
        ),

        // === HARIAN & PERALATAN RUMAH ===
        ArabicWord(
            id = 16,
            arabic = "البَيْت",
            rumi = "Al-Bait",
            malay = "Rumah",
            category = "Kehidupan Harian",
            exampleArabic = "بَيْتِي جَمِيلٌ وَنَظِيفٌ.",
            exampleMalay = "Rumah saya cantik dan bersih."
        ),
        ArabicWord(
            id = 17,
            arabic = "المَسْجِد",
            rumi = "Al-Masjid",
            malay = "Masjid",
            category = "Kehidupan Harian",
            exampleArabic = "أُصَلِّي الجَمَاعَةَ فِي المَسْجِدِ.",
            exampleMalay = "Saya solat berjemaah di masjid."
        ),
        ArabicWord(
            id = 18,
            arabic = "المُسْتَشْفَى",
            rumi = "Al-Mustashfa",
            malay = "Hospital",
            category = "Kehidupan Harian",
            exampleArabic = "يَذْهَبُ المَرِيضُ إِلَى المُسْتَشْفَى.",
            exampleMalay = "Pesakit pergi ke hospital."
        ),
        ArabicWord(
            id = 19,
            arabic = "المَطْعَم",
            rumi = "Al-Mat'am",
            malay = "Kedai Makan / Restoran",
            category = "Kehidupan Harian",
            exampleArabic = "نَتَنَاوَلُ العَشَاءَ فِي المَطْعَمِ.",
            exampleMalay = "Kami makan malam di restoran."
        ),
        ArabicWord(
            id = 20,
            arabic = "غُرْفَة",
            rumi = "Gurfah",
            malay = "Bilik",
            category = "Kehidupan Harian",
            exampleArabic = "هَذِهِ غُرْفَةُ النَّوْمِ.",
            exampleMalay = "Ini ialah bilik tidur."
        ),
        ArabicWord(
            id = 21,
            arabic = "كُرْسِيّ",
            rumi = "Kursiyy",
            malay = "Kerusi",
            category = "Kehidupan Harian",
            exampleArabic = "أَجْلِسُ عَلَى الكُرْسِيِّ.",
            exampleMalay = "Saya duduk di atas kerusi."
        ),
        ArabicWord(
            id = 22,
            arabic = "سَاعَة",
            rumi = "Sa'ah",
            malay = "Jam / Waktu",
            category = "Kehidupan Harian",
            exampleArabic = "السَّاعَةُ الآنَ الثَّالِثَةُ عَصْرًا.",
            exampleMalay = "Jam sekarang menunjukkan pukul tiga petang."
        ),
        ArabicWord(
            id = 23,
            arabic = "جَوَال / هَاتِف",
            rumi = "Jawwal / Hatif",
            malay = "Telefon Bimbit",
            category = "Kehidupan Harian",
            exampleArabic = "أَتَّصِلُ بِأُمِّي بِالجَوَالِ.",
            exampleMalay = "Saya menghubungi ibu saya dengan telefon bimbit."
        ),

        // === SYARIAH & IBADAH ===
        ArabicWord(
            id = 24,
            arabic = "الصَّلَاة",
            rumi = "As-Shalah",
            malay = "Solat / Sembahyang",
            category = "Al-Syariah (Ibadah)",
            exampleArabic = "الصَّلَاةُ عِمَادُ الدِّينِ.",
            exampleMalay = "Solat itu tiang agama."
        ),
        ArabicWord(
            id = 25,
            arabic = "الوُضُوء",
            rumi = "Al-Wudhu'",
            malay = "Wuduk / Air Sembahyang",
            category = "Al-Syariah (Ibadah)",
            exampleArabic = "الْوُضُوءُ شَرْطٌ لِصِحَّةِ الصَّلَاةِ.",
            exampleMalay = "Wuduk adalah syarat sah solat."
        ),
        ArabicWord(
            id = 26,
            arabic = "الرُّكْن",
            rumi = "Al-Rukn",
            malay = "Rukun / Tiang Asas (Amalan)",
            category = "Al-Syariah (Ibadah)",
            exampleArabic = "الرُّكْنُ لَا يَسْقُطُ عَمْدًا وَلَا سَهْوًا.",
            exampleMalay = "Rukun tidak gugur sama ada secara sengaja mahupun terlupa."
        ),
        ArabicWord(
            id = 27,
            arabic = "الشَّرْط",
            rumi = "As-Syarth",
            malay = "Syarat (Sesuatu sebelum ibadah)",
            category = "Al-Syariah (Ibadah)",
            exampleArabic = "طَهَارَةُ البَدَنِ شَرْطٌ لِلصَّلَاةِ.",
            exampleMalay = "Suci tubuh badan adalah syarat bagi solat."
        ),
        ArabicWord(
            id = 28,
            arabic = "الصَّوْم / الصِّيَام",
            rumi = "As-Shaum / As-Shiyam",
            malay = "Puasa",
            category = "Al-Syariah (Ibadah)",
            exampleArabic = "نَصُومُ فِي شَهْرِ رَمَضَانَ.",
            exampleMalay = "Kita berpuasa di bulan Ramadhan."
        ),

        // === USULUDDIN & AQIDAH ===
        ArabicWord(
            id = 29,
            arabic = "المُعْجِزَة",
            rumi = "Al-Mu'jizah",
            malay = "Mukjizat (Perkara luar biasa para nabi)",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "القُرْآنُ الكَرِيمُ هُوَ المُعْجِزَةُ الخَالِدَةُ.",
            exampleMalay = "Al-Quran Al-Karim adalah mukjizat yang kekal abadi."
        ),
        ArabicWord(
            id = 30,
            arabic = "الوُجُود",
            rumi = "Al-Wujud",
            malay = "Ada / Wujud (Sifat Nafsiah Allah)",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "وُجُودُ الخَلْقِ دَلِيلٌ عَلَى وُجُودِ الخَالِقِ.",
            exampleMalay = "Adanya makhluk adalah bukti atas adanya Pencipta."
        ),
        ArabicWord(
            id = 31,
            arabic = "القِدَم",
            rumi = "Al-Qidam",
            malay = "Sedia kala / Terdahulu tanpa awal",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "اللهُ اتَّصَفَ بِصِفَةِ القِدَمِ.",
            exampleMalay = "Allah disifatkan dengan sifat Qidam (tiada permulaan)."
        ),
        ArabicWord(
            id = 32,
            arabic = "البَقَاء",
            rumi = "Al-Baqa'",
            malay = "Kekal / Abadi tanpa akhir",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "البَقَاءُ لِلَّهِ وَحْدَهُ وَالدُّنْيَا فَانِيَةٌ.",
            exampleMalay = "Kekekalan itu milik Allah semata-mata, dan dunia ini binasa."
        ),
        ArabicWord(
            id = 33,
            arabic = "المَلَائِكَة",
            rumi = "Al-Mala'ikah",
            malay = "Malaikat (Makhluk daripada cahaya)",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "المَلَائِكَةُ لَا يَعْصُونَ اللهَ مَا أَمَرَهُمْ.",
            exampleMalay = "Malaikat tidak memaksiati Allah terhadap apa yang Dia perintahkan."
        ),
        ArabicWord(
            id = 34,
            arabic = "الرَّسُول",
            rumi = "Ar-Rasul",
            malay = "Utusan / Rasul (Penerima wahyu & penyampai)",
            category = "Usul al-Din (Aqidah)",
            exampleArabic = "أُرْسِلَ الرَّسُولُ رَحْمَةً لِلْعَالَمِينَ.",
            exampleMalay = "Rasul diutuskan sebagai rahmat untuk sekalian alam."
        )
    )
}
