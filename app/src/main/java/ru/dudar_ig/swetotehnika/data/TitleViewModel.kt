package ru.dudar_ig.swetotehnika.data

import androidx.lifecycle.ViewModel
import ru.dudar_ig.swetotehnika.R


class TitleViewModel: ViewModel() {
    val lines1 = mutableListOf<HomeData>()
    val lines2 = mutableListOf<HomeData>()
    init {
        val titles = listOf("Автоматика, УЗО","Управление освещением, датчики движения",
                            "Инструменты и приборы","Комплектующие для светильников",
                            "Комплектующие к кабелям","Лампы",
                            "Лента светодиодная","Светильники, прожекторы",
                            "Счетчики и трансформаторы","Удлинители, сетевые фильтры",
                            "Фонари","Электрические установочные изделия",
                            "Электротехника", "Электрощитовое оборудование" )

        val photos  = listOf<Int>(R.drawable.r0, R.drawable.r1, R.drawable.r2, R.drawable.r3,
                                    R.drawable.r4, R.drawable.r5, R.drawable.r6, R.drawable.r7,
                                    R.drawable.r8, R.drawable.r9, R.drawable.r10, R.drawable.r11,
                                    R.drawable.r12, R.drawable.r13)

        for (i in 0 until 14) {
            val homeData = HomeData()
            homeData.title = titles[i]
            homeData.foto = photos[i]
            if ( i % 2 == 0) {
                lines1.add(homeData)
            } else {
                lines2.add(homeData)
            }
        }
    }

}