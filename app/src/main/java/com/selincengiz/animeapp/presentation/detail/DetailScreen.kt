package com.selincengiz.animeapp.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.presentation.Dimens.ExtraSmallPadding
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding1
import com.selincengiz.animeapp.presentation.Dimens.SmallPadding
import com.selincengiz.animeapp.presentation.Dimens.SmallPadding2
import com.selincengiz.animeapp.presentation.common.GlowingCard
import com.selincengiz.animeapp.presentation.detail.components.Profile
import com.selincengiz.animeapp.presentation.detail.components.RatingBar
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme
import com.selincengiz.animeapp.ui.theme.BlueButtonColor
import com.selincengiz.animeapp.ui.theme.Dark
import com.selincengiz.animeapp.ui.theme.PurpleButtonColor
import com.selincengiz.animeapp.util.Constants.IMAGE_URL

@Composable
fun DetailScreen(movie: TvDetailUI) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(context).data(IMAGE_URL + movie.posterPath).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight(0.45f)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush =
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Dark.copy(alpha = 0.7f), Dark),
                                        startY = size.height / 2,
                                        endY = size.height,
                                    ),
                                size = size,
                            )
                        },
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .padding(horizontal = MediumPadding1)
                        .fillMaxWidth(),
            ) {
                Row(modifier = Modifier) {
                    Text(
                        modifier =
                            Modifier
                                .fillParentMaxWidth(0.4f),
                        text = movie.name ?: "",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = Bold),
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = movie.firstAirDate.toString().split('-')[0],
                        style = MaterialTheme.typography.bodyMedium,
                        modifier =
                            Modifier
                                .align(Alignment.Bottom),
                        color =
                            colorResource(
                                id = R.color.placeholder,
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .padding(top = 5.dp)
                            .align(Alignment.Top),
                ) {
                    RatingBar(rating = movie.voteAverage?.div(2) ?: 0.0f)
                    Text(
                        text = "From ${movie.voteCount} users",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color =
                            colorResource(
                                id = R.color.placeholder,
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Text(
                text = movie.networks?.get(0)?.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = MediumPadding1),
                color =
                    colorResource(
                        id = R.color.placeholder,
                    ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            var maxLines by remember {
                mutableStateOf(false)
            }

            Text(
                text = movie.overview ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier =
                    Modifier
                        .padding(horizontal = MediumPadding1)
                        .padding(top = SmallPadding)
                        .clickable { maxLines = !maxLines }
                        .animateContentSize(),
                color =
                    colorResource(
                        id = R.color.placeholder,
                    ),
                maxLines = if (maxLines) 15 else 5,
                overflow = TextOverflow.Ellipsis,
            )
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier =
                    Modifier
                        .padding(top = MediumPadding1)
                        .padding(horizontal = SmallPadding)
                        .fillMaxWidth()
                        .height(140.dp),
                horizontalArrangement = Arrangement.spacedBy(SmallPadding),
                verticalArrangement = Arrangement.spacedBy(SmallPadding2),
            ) {
                movie.createdBy?.let {
                    items(items = it) { createdBy ->
                        Profile(people = createdBy)
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
            ) {
                GlowingCard(
                    glowingColor = BlueButtonColor,
                    modifier =
                        Modifier
                            .padding(10.dp)
                            .size(width = 211.dp, height = 42.dp)
                            .border(
                                width = 2.dp,
                                brush =
                                    Brush.horizontalGradient(
                                        colors = listOf(BlueButtonColor, PurpleButtonColor),
                                    ),
                                shape = RoundedCornerShape(25.dp),
                            ),
                    cornersRadius = Int.MAX_VALUE.dp,
                    onClick = {
                        Intent(Intent.ACTION_VIEW).also {
                            it.data = Uri.parse(movie.homepage)
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    },
                ) {
                    Text(
                        text = "Watch Now",
                        color = Color.White,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    AnimeAppTheme {
        DetailScreen(
            movie =
                TvDetailUI(
                    "",
                    null,
                    "",
                    2,
                    "",
                    "",
                    "",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                ),
        )
    }
}
