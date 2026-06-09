package com.example.data

import com.example.model.Question

object QuestionsData {
    val questions = listOf(
        // ==========================================
        // KEYBOARD MODUL KHAS: ASAS KBD (Kurikulum Bersepadu Dini)
        // ==========================================
        Question(
            id = 101,
            text = "Apakah maksud perkataan \"المَدْرَسَة\" (Al-Madrasah) dalam Bahasa Melayu?",
            options = listOf("Hospital", "Sekolah", "Pejabat", "Masjid"),
            correctOptionIndex = 1,
            explanation = "\"المَدْرَسَة\" bermaksud Sekolah. Hospital ialah \"المُسْتَشْفَى\", Pejabat ialah \"المَكْتَب\", dan Masjid ialah \"المَسْجِد\".",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 102,
            text = "Antara berikut, yang manakah merupakan salah satu daripada syarat sah solat bagi seseorang Muslim?",
            options = listOf("Membaca Surah Al-Fatihah", "Mengetahui masuk waktu solat", "Membaca Selawat atas Nabi SAW", "Melakukan Ruku' dengan tamakninah"),
            correctOptionIndex = 1,
            explanation = "Mengetahui masuk waktu solat adalah syarat sah solat (yang mesti dipenuhi sebelum solat bermula). Pilihan lain ialah Rukun Solat (perbuatan di dalam solat).",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 103,
            text = "Sifat Wujud bagi Allah SWT dikategorikan di dalam kumpulan sifat...",
            options = listOf("Salbiah", "Nafsiah", "Ma'ani", "Ma'nawiyah"),
            correctOptionIndex = 1,
            explanation = "Sifat Nafsiah hanya mempunyai satu sifat sahaja iaitu sifat Al-Wujud (Ada).",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 104,
            text = "Apakah perkataan Bahasa Arab yang tepat untuk menggambarkan \"Pejabat\"?",
            options = listOf("المَكْتَبَة (Al-Maktabah)", "المَكْتَب (Al-Maktab)", "المَلْعَب (Al-Mal'ab)", "المَطْعَم (Al-Mat'am)"),
            correctOptionIndex = 1,
            explanation = "Pejabat ialah \"المَكْتَب\". \"المَكْتَبَة\" bermaksud perpustakaan/kedai buku, \"المَلْعَب\" bermaksud padang permainan, dan \"المَطْعَم\" bermaksud kedai makan/restoran.",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 105,
            text = "Berapakah bilangan rukun solat fardhu bagi mazhab Syafi'i?",
            options = listOf("10 rukun", "12 rukun", "13 rukun", "15 rukun"),
            correctOptionIndex = 2,
            explanation = "Rukun solat ada 13 semuanya yang terbahagi kepada rukun Qauli, Fi'li, dan Qalbi.",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 106,
            text = "Antara berikut, yang manakah merupakan sifat Salbiah bagi Allah SWT?",
            options = listOf("Al-Wujud", "Al-Qidam", "Al-Ilmu", "Al-Hayat"),
            correctOptionIndex = 1,
            explanation = "Sifat Salbiah ada 5: Al-Qidam, Al-Baqa', Mukhalafatuhu lil-hawadith, Qiyamuhu bi-nafsihi, dan Al-Wahdaniyah. Al-Wujud ialah Nafsiah manakala Al-Ilmu & Al-Hayat ialah Ma'ani.",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 107,
            text = "Apakah erti yang tepat bagi kalimah \"مُعَلِّمٌ\" (Mu'allim)?",
            options = listOf("Pelajar", "Guru", "Pengetua", "Kerani"),
            correctOptionIndex = 1,
            explanation = "\"مُعَلِّمٌ\" bermaksud Guru/Pendidik. Pelajar dalam bahasa Arab disebut \"طَالِبٌ\" atau \"تِلْمِيذٌ\".",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 108,
            text = "Salah satu Mukjizat Maknawi terbesar yang dikurniakan kepada Nabi Muhammad SAW dan kekal hingga kiamat ialah...",
            options = listOf("Al-Quran Al-Karim", "Peristiwa bulan terbelah dua", "Air memancar keluar dari celah jari baginda", "Makanan yang sedikit menjadi mencukupi untuk tentera"),
            correctOptionIndex = 0,
            explanation = "Al-Quran adalah Mukjizat Maknawi (intelektual/rohani) terbesar yang berkekalan. Pilihan lain adalah mukjizat Hissi (fizikal).",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 109,
            text = "Apakah hukum bagi berpuasa pada Hari Raya Aidilfitri dan Hari Raya Aidiladha?",
            options = listOf("Wajib", "Sunat", "Haram", "Makruh"),
            correctOptionIndex = 2,
            explanation = "Haram berpuasa pada Hari Raya Aidilfitri, Hari Raya Aidiladha, dan Hari-hari Tasyrik (11, 12, 13 Zulhijjah).",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 110,
            text = "Kitab samawi bernama Taurat telah diturunkan oleh Allah SWT kepada Nabi...",
            options = listOf("Nabi Daud AS", "Nabi Musa AS", "Nabi Isa AS", "Nabi Ibrahim AS"),
            correctOptionIndex = 1,
            explanation = "Kitab Taurat diturunkan kepada Nabi Musa AS, Zabur kepada Nabi Daud AS, Injil kepada Nabi Isa AS, dan Al-Quran kepada Nabi Muhammad SAW.",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 111,
            text = "Lengkapkan ayat ini: (هَذَا ..... جَدِيدٌ) dengan perkataan yang betul dari segi tatabahasa Arab.",
            options = listOf("حَقِيبَةٌ (Beg)", "كِتَابٌ (Buku)", "مِسْطَرَةٌ (Pembaris)", "نَافِذَةٌ (Tingkap)"),
            correctOptionIndex = 1,
            explanation = "Kata tunjuk \"هَذَا\" (Muzakkar/Lelaki) memerlukan kata nama Muzakkar tanpa \"Ta Marbutah\" (ة). \"كِتَابٌ\" adalah jawapan muzakkar yang betul.",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 112,
            text = "Solat sunat dua rakaat yang dikerjakan sebelum atau selepas solat fardhu bagi mengiringi/melengkapkannya dipanggil...",
            options = listOf("Solat Sunat Tarawih", "Solat Sunat Tahajjud", "Solat Sunat Rawatib", "Solat Sunat Witir"),
            correctOptionIndex = 2,
            explanation = "Solat sunat yang mengiringi solat fardhu dipanggil Solat Sunat Rawatib (sama ada Qabliyyah - sebelum, atau Ba'diyyah - selepas).",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 113,
            text = "Apakah ganti nama (dhamir) yang sesuai untuk 'أَحْمَدُ وَعَلِيٌّ يَكْتُبَانِ الرِّسَالَةَ'?",
            options = listOf("أَنْتُمَا (Antuma)", "هُمَا (Huma)", "هُمْ (Hum)", "نَحْنُ (Nahnu)"),
            correctOptionIndex = 1,
            explanation = "Bagi subjek dua orang lelaki (Ahmad & Ali), ganti nama ghaib (kata ganti diri ketiga) yang sepadan ialah 'هُمَا' (mereka berdua).",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 114,
            text = "Pilih perkataan kata kerja lampau (Fi'il Madhi) yang sesuai bagi kata ganti diri 'أَنْتُمْ' (kamu semua lelaki).",
            options = listOf("ذَهَبْتُمْ (Dzahabtum)", "ذَهَبْنَا (Dzahabna)", "ذَهَبُوا (Dzahabu)", "تَذْهَبُونَ (Tadzhabun)"),
            correctOptionIndex = 0,
            explanation = "Kata kerja Madhi bagi 'أَنْتُمْ' berakhir dengan dhomir rafa' muttasil iaitu 'تُمْ', menjadikan sebutan 'ذَهَبْتُمْ'.",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 115,
            text = "Apakah maksud bagi perkataan 'الحَافِلَةُ' (Al-Hafilah) dalam kategori pengangkutan harian?",
            options = listOf("Kereta", "Bas", "Kereta Api", "Motosikal"),
            correctOptionIndex = 1,
            explanation = "'الحَافِلَةُ' bermaksud Bas. Kereta ialah 'السَّيَّارَةُ', Kereta api ialah 'القِطَارُ', dan Motosikal ialah 'الدَّرَّاجَةُ Nārīyah'.",
            category = "KBD",
            subCategory = "Al-Lughah"
        ),
        Question(
            id = 116,
            text = "Antara berikut, yang manakah merupakan rukun wuduk yang wajib dipenuhi bagi mazhab Syafi'i?",
            options = listOf("Berkumur-kumur", "Membasuh kedua-dua belah telinga", "Menyapu sebahagian daripada kepala", "Membasuh kaki hingga melebihi lutut"),
            correctOptionIndex = 2,
            explanation = "Rukun wuduk bagi mazhab Syafi'i ada 6, dan salah satu daripadanya ialah menyapu sebahagian daripada kepala dengan air. Berkumur dan membasuh telinga adalah sunat wuduk.",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 117,
            text = "Apakah hukum bagi amalan menyapu air ke kedua-dua belah telinga ketika mengambil wuduk?",
            options = listOf("Wajib", "Sunat", "Harus", "Makruh"),
            correctOptionIndex = 1,
            explanation = "Menyapu air ke telinga semasa berwuduk adalah sunat muakkadah (sunat yang dituntut) dan bukanlah salah satu rukun wuduk wajib.",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 118,
            text = "Antara berikut, yang manakah merupakan waktu yang dilarang (HARAM) untuk mendirikan solat sunat mutlak?",
            options = listOf("Selepas mendirikan solat fardhu Subuh sehingga naik matahari", "Sebelum masuk masuknya waktu solat fardhu Zohor", "Selepas solat fardhu Maghrib sebelum Isyak", "Antara laungan azan dan iqamah solat fardhu Isyak"),
            correctOptionIndex = 0,
            explanation = "Terdapat lima waktu haram solat sunat mutlak (tahrim), salah satunya ialah selepas solat fardhu Subuh sehingga matahari terbit sepenuhnya (syuruk).",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 119,
            text = "Sembahyang sunat dua rakaat berjemaah yang didirikan bagi memohon diturunkan hujan oleh Allah SWT ketika musim kemarau dipanggil...",
            options = listOf("Solat Sunat Istisqa'", "Solat Sunat Istikharah", "Solat Sunat Khusuf", "Solat Sunat Tarawih"),
            correctOptionIndex = 0,
            explanation = "Solat Sunat Istisqa' dituntut syarak ketika bumi menghadapi kesukaran air / kemarau panjang bagi memohon belas rupa rahmat hujan.",
            category = "KBD",
            subCategory = "Al-Syariah"
        ),
        Question(
            id = 120,
            text = "Sifat 'Al-Qidam' bagi Allah SWT dalam kategori Sifat Dua Puluh bermaksud...",
            options = listOf("Kekal abadi", "Sedia kala / Terdahulu tiada permulaan", "Berbeza dengan makhluk ciptaan", "Berdiri dengan sendiri"),
            correctOptionIndex = 1,
            explanation = "Sifat nafiah/salbiah 'Al-Qidam' bererti sedia ada Allah SWT tanpa terdahulu oleh sebarang permulaan atau ciptaan makhluk.",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 121,
            text = "Apakah rukun kepercayaan dan nama malaikat yang ditugaskan oleh Allah SWT untuk mencabut nyawa makhluk bernyawa?",
            options = listOf("Malaikat Jibril AS", "Malaikat Izrail AS", "Malaikat Israfil AS", "Malaikat Mikail AS"),
            correctOptionIndex = 1,
            explanation = "Malaikat Izrail AS diberikan tanggungjawab mencabut nyawa seluruh hidupan atas arahan Allah SWT.",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),
        Question(
            id = 122,
            text = "Berapakah bilangan sifat WAJIB bagi Rasul yang wajib kita imani dan hayati?",
            options = listOf("3 sifat utama", "4 sifat utama", "5 sifat utama", "20 sifat utama"),
            correctOptionIndex = 1,
            explanation = "Sifat wajib bagi para Rasul ada empat: Siddiq (Benar), Amanah (Dipercayai), Tabligh (Menyampaikan), dan Fatanah (Bijaksana).",
            category = "KBD",
            subCategory = "Usul al-Din"
        ),

        // ==========================================
        // BAHAGIAN A: KECERDASAN INSANIAH (EQ & Sahsiah)
        // ==========================================
        Question(
            id = 201,
            text = "Rakan karib anda mendapat keputusan cemerlang dalam peperiksaan sedangkan keputusan anda kecundang. Apakah tindakan terbaik bagi EQ mulia?",
            options = listOf(
                "Menjauhkan diri daripada dia kerana berasa rendah diri.",
                "Mengucapkan tahniah dengan ikhlas dan mengajaknya berkongsi tips mengulang kaji.",
                "Menganggap dia bernasib baik dan soalan peperiksaan tidak adil.",
                "Menyebarkan khabar angin bahawa dia meniru semasa ujian."
            ),
            correctOptionIndex = 1,
            explanation = "Mempunyai EQ yang tinggi bermaksud anda dapat mengurus emosi diri sendiri dengan baik, menghargai kejayaan orang lain tanpa dengki, dan menjadikannya pemangkin untuk kejayaan diri.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Kematangan Emosi"
        ),
        Question(
            id = 202,
            text = "Apabila berjalan di koridor, anda nampak seorang guru sedang keberatan membawa timbunan fail yang banyak. Apakah tindakan anda?",
            options = listOf(
                "Berpura-pura meneliti papan kenyataan berdekatan untuk mengelak dipanggil.",
                "Menghampiri guru dengan sopan dan menawarkan bantuan untuk membawakan fail.",
                "Menyuruh murid lain di tepi untuk segera menolong guru tersebut.",
                "Hanya memerhatikan dan mengambil gambar guru tersebut untuk dimuat naik."
            ),
            correctOptionIndex = 1,
            explanation = "Sifat tolong-menolong, empati, dan beradab sopan kepada guru merupakan aspek penting sahsiah cemerlang dalam penilaian Kecerdasan Insaniah.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Empati"
        ),
        Question(
            id = 203,
            text = "Sekiranya pihak guru melantik anda sebagai ketua kumpulan bagi menyiapkan satu projek sains penting, bagaimanakah cara anda mengemudi kumpulan?",
            options = listOf(
                "Menentukan semua bahagian kerja secara peribadi tanpa mendengar pandangan rakan.",
                "Berbincang dengan harmoni bagi mengasingkan skop kerja mengikut kekuatan ahli.",
                "Membiarkan semua ahli membuat tugas kegemaran masing-masing tanpa pengawasan.",
                "Menyerahkan keseluruhan tugasan untuk diselesaikan oleh ahli manakala anda berehat."
            ),
            correctOptionIndex = 1,
            explanation = "Kepemimpinan yang berkesan melibatkan komunikasi dua hala, penghargaan kebolehan ahli, dan pengagihan tugas yang sasar dan adil.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Kepimpinan"
        ),
        Question(
            id = 204,
            text = "Seorang murid menuduh anda mencuri alat tulisnya di hadapan khalayak kelas dengan sangat kuat. Bagaimanakah tindak balas matang anda?",
            options = listOf(
                "Melenting marah dan bertindak memukul murid tersebut untuk membalas dendam.",
                "Menerangkan keadaan secara sabar, menunjukkan kotak pensel anda, dan memintanya menyemak semula.",
                "Melarikan diri keluar dari sekolah dan menangis di rumah sepanjang malam.",
                "Mengambil dan menyembunyikan beg murid tersebut senyap-senyap sebagai hukuman."
            ),
            correctOptionIndex = 1,
            explanation = "Menyelesaikan konflik secara rasional, tenang, dan bersandar bukti berbanding menyalakan emosi agresif mencerminkan kawalan kendiri yang hebat.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Penyelesaian Konflik"
        ),
        Question(
            id = 205,
            text = "Ketika anda sedang sibuk menulis esei bahasa, rakan sebelah meminta ihsan anda untuk menunjukkan jalan penyelesaian sifir matematik. Apakah tindakan anda?",
            options = listOf(
                "Memarahinya kerana mengganggu tumpuan penulisan anda.",
                "Menghentikan kerja seketika, mengajar konsep sifir berkenaan dengan bersungguh, kemudian menyambung esei.",
                "Memberikan buku jawapan anda untuk disalin bulat-bulat tanpa mendidiknya.",
                "Mengadu kepada ketua darjah bahawa dia malas mengira sendiri."
            ),
            correctOptionIndex = 1,
            explanation = "Sedia berkongsi ilmu dan membimbing rakan sebaya secara berhemah membina persekitaran pembelajaran kolaboratif yang sihat.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Nilai Murni"
        ),
        Question(
            id = 206,
            text = "Pandangan cadangan yang anda bentangkan semasa mesyuarat jawatankuasa kelab ditolak sebulat suara oleh ahli-ahli lain. Reaksi anda?",
            options = listOf(
                "Mengambil keputusan untuk keluar serta-merta dan tidak mahu lagi menghadirkan diri.",
                "Menerima keputusan majoriti dengan lapang dada dan terus memberikan sokongan penuh.",
                "Merosakkan peralatan bilik bagi menunjukkan rasa tidak puas hati anda.",
                "Merancang taktik di belakang tabir untuk memburukkan keputusan ahli mesyuarat."
            ),
            correctOptionIndex = 1,
            explanation = "Amalan faham bertoleransi dan menghormati keputusan demokrasi majoriti tanpa sifat dendam peribadi menunjukkan keinsanan matang.",
            category = "KECERDASAN_INSANIAH",
            subCategory = "Demokrasi & Toleransi"
        ),

        // ==========================================
        // BAHAGIAN B: KECERDASAN INTELEK (IQ & Akademik)
        // ==========================================
        Question(
            id = 301,
            text = "Jika hari ini ialah hari Selasa, apakah nama hari selepas tempoh 100 hari berlalu dari sekarang?",
            options = listOf("Rabu", "Khamis", "Jumaat", "Sabtu"),
            correctOptionIndex = 1,
            explanation = "Terdapat 7 hari dalam seminggu. 100 dibahagi 7 memberi baki 2 (98 adalah gandaan 7). Oleh itu, bergerak 2 hari selepas hari Selasa ialah hari Khamis.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Penaakulan Logik"
        ),
        Question(
            id = 302,
            text = "Siapakah tokoh sejarah yang dijulang sebagai Perdana Menteri Malaysia yang pertama (juga digelar Bapa Kemerdekaan)?",
            options = listOf("Tun Abdul Razak", "Tunku Abdul Rahman", "Tun Hussein Onn", "Tun Dr. Mahathir Mohamad"),
            correctOptionIndex = 1,
            explanation = "Tunku Abdul Rahman Putra Al-Haj merupakan Perdana Menteri Malaysia pertama yang mengisytiharkan kemerdekaan negara pada 31 Ogos 1957.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Pengetahuan Am"
        ),
        Question(
            id = 303,
            text = "Organ kritikal manakah di dalam tubuh manusia yang memikul tanggungjawab utama mempam darah beroksigen ke serata tubuh?",
            options = listOf("Paru-paru", "Jantung", "Hati", "Buah Pinggang"),
            correctOptionIndex = 1,
            explanation = "Jantung berfungsi mengepam darah beroksigen ke seluruh tisu manakala Peparu bertanggungjawab menukar karbon dioksida kepada oksigen.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Sains Hayat"
        ),
        Question(
            id = 304,
            text = "Cari jawapan akhir bagi ungkapan matematik berikut: 25 + 15 × 2 - 10",
            options = listOf("70", "45", "55", "35"),
            correctOptionIndex = 1,
            explanation = "Mengikut hukum BODMAS/PEMDAS, operasi darab diselesaikan dahulu: 15 × 2 = 30. Kemudian, selesaikan tambah dan tolak secara berurutan: 25 + 30 - 10 = 55 - 10 = 45.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Matematik"
        ),
        Question(
            id = 305,
            text = "Apakah erti sebenar simpulan bahasa Melayu \"panjang tangan\"?",
            options = listOf(
                "Suka memberi pertolongan kepada jiran tetangga.",
                "Suka mengambil barang atau mencuri barang orang lain.",
                "Sangat tangkas melakukan pelbagai kerja harian.",
                "Mudah baran atau cepat naik berang apabila diusik."
            ),
            correctOptionIndex = 1,
            explanation = "\"Panjang tangan\" bermaksud orang yang suka mencelok atau mencuri barangan peribadi milik orang lain.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Literasi Bahasa"
        ),
        Question(
            id = 306,
            text = "Apakah nama planet yang menduduki kedudukan fizikal yang paling dekat dengan pusat Matahari dalam sistem suria kita?",
            options = listOf("Zuhal", "Utarid", "Marikh", "Zuhrah"),
            correctOptionIndex = 1,
            explanation = "Utarid (Mercury) menduduki orbit paling hampir dengan Matahari, diikuti dengan Zuhrah, Bumi, Marikh, Musytari, Zuhal, Uranus, dan Neptun.",
            category = "KECERDASAN_INTELEK",
            subCategory = "Sains Fizikal"
        )
    )
}
