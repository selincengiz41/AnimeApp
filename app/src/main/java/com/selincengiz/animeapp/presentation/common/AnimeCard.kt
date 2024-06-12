package com.selincengiz.animeapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.Dimens
import com.selincengiz.animeapp.presentation.Dimens.ExtraSmallPadding
import com.selincengiz.animeapp.presentation.Dimens.MaxText
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme
import com.selincengiz.animeapp.util.Constants.IMAGE_URL

@Composable
fun AnimeCard(
    modifier: Modifier = Modifier,
    tv: TvUI,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .size(Dimens.AnimeCardSize),
            model = ImageRequest.Builder(context).data(IMAGE_URL + tv.posterPath).build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = modifier.height(ExtraSmallPadding))

        Text(
            text = tv.name ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .widthIn(max = MaxText),
            textAlign = TextAlign.Center,
            color = colorResource(
                id = R.color.text_title
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = modifier.height(ExtraSmallPadding))

        Text(
            text = tv.firstAirDate.toString().split('-')[0],
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = colorResource(
                id = R.color.placeholder
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AnimeCardPreview() {
    AnimeAppTheme {
        AnimeCard(
            tv = TvUI(
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
                false
            )
        ) {

        }
    }
}