package com.selincengiz.animeapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.Dimens
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme
import com.selincengiz.animeapp.util.Constants

@Composable
fun AnimeSearchCard(
    modifier: Modifier = Modifier,
    tv: TvUI,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onClick() },
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .size(Dimens.AnimeCardSize),
            placeholder = painterResource(id = R.drawable.placeholder),
            model = ImageRequest.Builder(context).data(Constants.IMAGE_URL + tv.posterPath).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = tv.name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color =
                    colorResource(
                        id = R.color.text_title,
                    ),
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AnimeSearchCardPreview() {
    AnimeAppTheme {
        AnimeSearchCard(
            tv =
                TvUI(
                    adult = false,
                    backdropPath = "2",
                    firstAirDate = "2022",
                    genreIds = listOf(2),
                    id = 2,
                    mediaType = "2",
                    name = "Secret Wars",
                    originCountry = listOf("2"),
                    originalLanguage = "2",
                    originalName = "2",
                    overview = "2",
                    popularity = 2.0,
                    posterPath = "/fqv8v6AycXKsivp1T5yKtLbGXce.jpg",
                    voteAverage = 2.0,
                    voteCount = 2,
                    false,
                ),
        ) {
        }
    }
}
