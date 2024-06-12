package com.selincengiz.animeapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.selincengiz.animeapp.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)

val pages = listOf(
    Page(
        title = "En Sevdiğiniz Animeleri Keşfedin",
        description = "Gelişmiş arama özelliğimizle istediğiniz animeyi kolayca bulun.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Detaylara Dalın",
        description = "Her anime hakkında ayrıntılı bilgi edinin. Bölüm listeleri, karakter bilgileri, özetler ve kullanıcı yorumları ile en sevdiğiniz seriler hakkında daha fazla bilgiye sahip olun.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Anime Önerileri",
        description = "Size özel listeler ve önerilerle yeni favori serilerinizi keşfedin.",
        image = R.drawable.onboarding1
    )
)