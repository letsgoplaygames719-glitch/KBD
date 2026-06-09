package com.example.data

import com.example.model.KamusQuizQuestion

object KamusQuizData {
    val questions = listOf(
        KamusQuizQuestion(
            id = 1,
            question = "Antara berikut, manakah terjemahan yang betul bagi kalimah 'حَافِلَةٌ' (Hafilah)?",
            options = listOf("Kapal terbang", "Bas", "Kereta api", "Motosikal"),
            correctOptionIndex = 1,
            explanation = "Kalimah 'حَافِلَةٌ' bermaksud Bas dalam Bahasa Arab. Ia diletakkan di bawah sub-kategori kosa kata pengangkutan.",
            type = "Kosa Kata"
        ),
        KamusQuizQuestion(
            id = 2,
            question = "Apakah peranan tatabahasa bagi perkataan yang bergaris dalam ayat: 'المَدْرَسَةُ _جَمِيلَةٌ_.'?",
            options = listOf("Mubtada' (Subjek)", "Khabar (Predikat)", "Fa'il (Pelaku)", "Fi'l Mudari'"),
            correctOptionIndex = 1,
            explanation = "Perkataan 'جَمِيلَةٌ' berfungsi sebagai Khabar (predikat) kerana ia merupakan Ism Nakirah yang menerangkan keadaan Mubtada' iaitu 'المَدْرَسَةُ'.",
            type = "Nahu"
        ),
        KamusQuizQuestion(
            id = 3,
            question = "Kalimah 'يَكْتُبُ' (Yaktubu) dikategorikan sebagai:",
            options = "Fi'l Madi (Telah berlaku), Fi'l Mudari' (Sedang berlaku), Ism Mufrad (Satu), Harf Jar (Sendi nama)".split(", "),
            correctOptionIndex = 1,
            explanation = "Perkataan 'يَكْتُبُ' ialah Fi'l Mudari' (kata kerja masa kini/akan datang) untuk subjek 'Dia (seorang lelaki)'. Ia ditandai dengan huruf tambahan 'Ya' (ي) di hadapan.",
            type = "Sarf"
        ),
        KamusQuizQuestion(
            id = 4,
            question = "Bagaimanakah cara menukar kata mufrad 'مُعَلِّمٌ' (seorang guru) kepada muthanna (dua orang guru)?",
            options = listOf("Menambah 'Alif' (ـا) di hadapan", "Menambah 'Waw dan Nun' (ـونَ) di hujung", "Menambah 'Alif dan Nun' (ـانِ) di hujung", "Menukar menjadi jam'u taksir"),
            correctOptionIndex = 2,
            explanation = "Bagi kata nama Muthanna (dua), kita menambah Alif dan Nun (ـانِ) atau Ya dan Nun (ـيْنِ) di hujung kata mufrad asal, menjadikannya 'مُعَلِّمَانِ'.",
            type = "Nahu & Sarf"
        ),
        KamusQuizQuestion(
            id = 5,
            question = "Apakah terjemahan bahasa Arab bagi 'Kereta api'?",
            options = listOf("قِطَارٌ", "حَافِلَةٌ", "طَائِرَةٌ", "سَفِينَةٌ"),
            correctOptionIndex = 0,
            explanation = "Kereta api dalam Bahasa Arab ialah 'قِطَارٌ' (Qithar). Manakala 'سَفِينَةٌ' bermaksud kapal laut dan 'طَائِرَةٌ' bermaksud kapal terbang.",
            type = "Kosa Kata"
        ),
        KamusQuizQuestion(
            id = 6,
            question = "Kenapa kata kerja ini berbeza dalam ayat berikut: \n1 - خَرَجَ المُعَلِّمُ.\n2 - خَرَجَتْ المُعَلِّمَةُ.",
            options = listOf(
                "Kerana perbezaan masa berlaku",
                "Kerana perbezaan bilangan jantina pelaku (Muzakkar & Mu'annath)",
                "Satu fi'l madi dan satu fi'l mudari'",
                "Satu ayat aktif dan satu ayat pasif"
            ),
            correctOptionIndex = 1,
            explanation = "Dalam ayat pertama pelakunya lelaki (المُعَلِّمُ), maka fi'l madi ditulis 'خَرَجَ'. Dalam ayat kedua pelakunya perempuan (المُعَلِّمَةُ), maka fi'l madi ditambah 'ta sakinah' (تْ) menjadikannya 'خَرَجَتْ'.",
            type = "Nahu"
        ),
        KamusQuizQuestion(
            id = 7,
            question = "Apakah maksud bagi kalimah 'هَاتِفٌ' (Hatif)?",
            options = listOf("Televisyen", "Telefon bimbit", "Komputer", "Mesin basuh"),
            correctOptionIndex = 1,
            explanation = "Kalimah 'هَاتِفٌ' (atau هَاتِفٌ مَحْمُولٌ) dalam Bahasa Arab bermaksud telefon bimbit. Ia sebahagian kosa kata Peralatan Harian.",
            type = "Kosa Kata"
        ),
        KamusQuizQuestion(
            id = 8,
            question = "Dalam pembahagian kata (أقسام الكلمة), manakah antara berikut dikategorikan sebagai 'Harf'?",
            options = listOf("أَحْمَدُ", "يَذْهَبُ", "إِلَى", "قَلَمٌ"),
            correctOptionIndex = 2,
            explanation = "'إِلَى' (ke/kepada) ialah Harf Jar (sendi kata). Manakah 'قَلَمٌ' dan 'أَحْمَدُ' ialah Ism (kata nama), manakala 'يَذْهَبُ' ialah Fi'l (kata kerja).",
            type = "Nahu"
        ),
        KamusQuizQuestion(
            id = 9,
            question = "Bermaksud apakah perkataan Arab 'تُفَّاحٌ' (Tuffah)?",
            options = listOf("Epal", "Pisang", "Oren", "Tembikai"),
            correctOptionIndex = 0,
            explanation = "'تُفَّاحٌ' bermaksud Epal. Ia kosa kata buah-buahan yang kerap ditanya dalam asas perbualan kurikulum dini.",
            type = "Kosa Kata"
        ),
        KamusQuizQuestion(
            id = 10,
            question = "Lengkapkan ayat ini dengan Fa'il yang sesuai: 'قَرَأَ [...] الكِتَابَ.'",
            options = listOf("الطَّالِبَةُ", "الطَّالِبُ", "الطَّالِبَانِ (untuk kata kerja muthanna)", "الطُّلَّابُ"),
            correctOptionIndex = 1,
            explanation = "Kerana kata kerja 'قَرَأَ' adalah untuk 'Mufrad Muzakkar' (Kata tunggal lelaki), maka pelaku (Fa'il) yang paling sesuai ialah kata tunggal lelaki iaitu 'الطَّالِبُ' (Pelajar itu).",
            type = "Nahu"
        )
    )
}
