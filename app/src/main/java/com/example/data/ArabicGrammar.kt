package com.example.data

import com.example.model.GrammarTopic
import com.example.model.GrammarExample

object ArabicGrammar {
    val topics = listOf(
        GrammarTopic(
            id = 1,
            title = "Pembahagian Kata",
            arabicTitle = "أَقْسَامُ الكَلِمَة",
            category = "Nahu (Sintaksis)",
            explanation = "Dalam Bahasa Arab, setiap perkataan yang diucapkan atau ditulis terbahagi kepada tiga komponen utama: Ism (Kata Nama), Fi'l (Kata Kerja), dan Harf (Sendi Kata).",
            ruleSummary = "1. Ism (الاسم): Nama bagi benda, manusia, tempat, atau sifat.\n2. Fi'l (الفعل): Kata kerja yang terikat dengan masa.\n3. Harf (الحرف): Huruf atau sendi kata yang menunjukkan maksud lengkap apabila bersambung dengan Ism atau Fi'l.",
            examples = listOf(
                GrammarExample("كِتَابٌ (Kiatb)", "Ism: Buku (Mempunyai tanwin di hujung)"),
                GrammarExample("يَكْتُبُ (Yaktubu)", "Fi'l: Dia sedang menulis (Terikat masa sekarang)"),
                GrammarExample("فِي (Fi)", "Harf: Di dalam / Pada")
            )
        ),
        GrammarTopic(
            id = 2,
            title = "Kata Kerja Lampau vs Sekarang",
            arabicTitle = "الفِعْلُ المَاضِي وَالمُضَارِع",
            category = "Sarf (Morfologi)",
            explanation = "Fi'l terbahagi kepada dua jenis utama mengikut garis masa: Fi'l Madi (Lalu/Past) dan Fi'l Mudari' (Sekarang/Akan Datang/Present).",
            ruleSummary = "1. Fi'l Madi (الماضي): Hujungnya berbaris atas (Fathah) secara asal untuk kata tunggal lelaki.\n2. Fi'l Mudari' (المضارع): Mempunyai huruf tambahan (أ، ن، ي، ت) di hadapan kata kerja dasar.",
            examples = listOf(
                GrammarExample("دَرَسَ أَحْمَدُ", "Fi'l Madi: Ahmad telah belajar."),
                GrammarExample("يَدْرُسُ أَحْمَدُ", "Fi'l Mudari': Ahmad sedang belajar (Imbuhan 'ya' di hadapan)."),
                GrammarExample("كَتَبَتْ فَاطِمَةُ", "Fi'l Madi: Fatimah telah menulis (Imbuhan 'ta' mati di belakang melambangkan perempuan).")
            )
        ),
        GrammarTopic(
            id = 3,
            title = "Bilangan Kata Nama",
            arabicTitle = "المُفْرَدُ وَالمُثَنَّى وَالجَمْع",
            category = "Nahu & Sarf",
            explanation = "Sistem bilangan kata nama (Ism) terbahagi kepada tiga kumpulan berbeza: Mufrad (Satu), Muthanna (Dua), dan Jam'u (Tiga atau lebih).",
            ruleSummary = "1. Mufrad (المفرد): Kata nama asal.\n2. Muthanna (المثنى): Tambah 'Alif dan Nun' (ـانِ) atau 'Ya dan Nun' (ـيْنِ) di belakang.\n3. Jam'u (الجمع): Boleh jadi Jam'u Muzakkar Salim (Lelaki - ـونَ/ـينَ), Jam'u Mu'annath Salim (Perempuan - ـات), atau Jam'u Taksir (Pecah/tidak sekata).",
            examples = listOf(
                GrammarExample("مُعَلِّمٌ", "Mufrad: Seorang guru (lelaki)."),
                GrammarExample("مُعَلِّمَانِ", "Muthanna: Dua orang guru (lelaki) dengan tambahan 'Alif & Nun'."),
                GrammarExample("مُعَلِّمُونَ", "Jam'u Muzakkar Salim: Ramai guru (lelaki, 3 ke atas)."),
                GrammarExample("مُعَلِّمَاتٌ", "Jam'u Mu'annath Salim: Ramai guru (perempuan, 3 ke atas).")
            )
        ),
        GrammarTopic(
            id = 4,
            title = "Subjek & Predikat Kata Nama",
            arabicTitle = "المُبْتَدَأُ وَالخَبَر",
            category = "Nahu (Sintaksis)",
            explanation = "Setiap ayat penyata (Jumlah Ismiyah) yang bermula dengan kata nama (Ism) mestilah terbentuk daripada dua tiang utama: Mubtada' (Subjek) dan Khabar (Predikat/Penerang). Kedua-duanya mestilah berbaris hadapan (Marfu').",
            ruleSummary = "1. Mubtada' (المبتدأ): Ism makrifah (tertentu, biasanya mulakan dengan ال) di awal ayat.\n2. Khabar (الخبر): Ism nakirah (umum) yang menerangkan atau menyempurnakan cerita Mubtada'.",
            examples = listOf(
                GrammarExample("المَدْرَسَةُ جَمِيلَةٌ.", "Mubtada' ialah 'Al-Madrasah' (Sekolah itu) & Khabar ialah 'Jamilah' (cantik)."),
                GrammarExample("الطَّالِبُ مُجْتَهِدٌ.", "Mubtada' ialah 'Al-Thalib' (Pelajar itu) & Khabar ialah 'Mujtahid' (rajin).")
            )
        ),
        GrammarTopic(
            id = 5,
            title = "Pelaku Perbuatan",
            arabicTitle = "الفَاعِل",
            category = "Nahu (Sintaksis)",
            explanation = "Apabila sesuatu kata kerja (Fi'l) disebut, mestilah ada kata nama yang bertindak selaku pelaku (Fa'il). Fa'il sentiasa berbaris hadapan (Marfu') dan terletak selepas kata kerja.",
            ruleSummary = "Setiap Fi'l memerlukan Fa'il. Sekiranya pelaku itu perempuan (Mu'annath), kata kerja juga wajib ditukar bersesuaian dengan jantina pelaku.",
            examples = listOf(
                GrammarExample("خَرَجَ المُعَلِّمُ.", "Fa'il: Guru itu (Al-Mu'allim) telah keluar. Baris depan di hujung pelakunya."),
                GrammarExample("خَرَجَتْ المُعَلِّمَةُ.", "Muzakkar vs Mu'annath: Guru perempuan telah keluar (Fi'l ditambah 'ta' sukun di hujung).")
            )
        )
    )
}
