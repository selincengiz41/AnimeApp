package com.selincengiz.animeapp.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.presentation.Dimens
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}


@Composable
fun AnimeCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(Dimens.AnimeCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Spacer(modifier = modifier.height(Dimens.ExtraSmallPadding))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(65.dp)
                .height(21.dp)
                .shimmerEffect()
        )

        Spacer(modifier = modifier.height(Dimens.ExtraSmallPadding))
    }
}

@Composable
fun AnimeSearchCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(Dimens.AnimeCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.7f)
                    .height(30.dp)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun ShimmerEffect(type: String) {
    when (type) {
        "search" -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
                modifier = Modifier.padding(start = 20.dp)
            ) {
                repeat(10) {
                    AnimeSearchCardShimmerEffect(modifier = Modifier)
                }
            }
        }

        "normal" -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
                modifier = Modifier.padding(start = 20.dp)
            ) {
                repeat(10) {
                    AnimeCardShimmerEffect(modifier = Modifier)
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AnimeCardShimmerEffectPreview() {
    AnimeAppTheme {
        Column {
            ShimmerEffect("search")
        }
    }
}


