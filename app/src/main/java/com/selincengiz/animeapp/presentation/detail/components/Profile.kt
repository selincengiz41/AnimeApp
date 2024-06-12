package com.selincengiz.animeapp.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.data.remote.dto.CreatedBy
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme
import com.selincengiz.animeapp.ui.theme.BlueButtonColor
import com.selincengiz.animeapp.ui.theme.Dark
import com.selincengiz.animeapp.ui.theme.Grey
import com.selincengiz.animeapp.ui.theme.PurpleButtonColor
import com.selincengiz.animeapp.util.Constants

@Composable
fun Profile(people: CreatedBy) {
    val context = LocalContext.current

    Row(modifier = Modifier.wrapContentWidth()) {

        AsyncImage(
            model = ImageRequest.Builder(context).data(Constants.IMAGE_URL + people.profilePath)
                .error(
                    R.drawable.ic_person
                )
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_person),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .shadow(elevation = 100.dp, clip = true, shape = CircleShape)
                .clip(CircleShape)
                .size(52.dp)
                .border(
                    width = 2.dp, brush = Brush.horizontalGradient(
                        colors = listOf(BlueButtonColor, PurpleButtonColor)
                    ),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .absoluteOffset(x = -20.dp, y = 2.dp)
                .zIndex(-1f)
                .height(48.dp)
                .width(180.dp)
                .border(
                    width = 1.dp, brush = Brush.linearGradient(
                        colors = listOf(
                            Grey.copy(alpha = 0.0f),
                            Grey.copy(alpha = 0.0f),
                            Grey.copy(alpha = 0.1f),
                            Grey.copy(alpha = 0.1f),
                            Grey.copy(alpha = 0.2f),
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = Dark, shape = RoundedCornerShape(16.dp)),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Center,
                text = people.name.toString(),
                maxLines = 2,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun ProfilePreview() {
    AnimeAppTheme {
        Profile(
            CreatedBy(
                null,
                null,
                null,
                "Maria Espaseasfs",
                null,
                null
            ),
            )
    }
}