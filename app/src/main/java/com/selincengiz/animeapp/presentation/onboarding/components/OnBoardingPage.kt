package com.selincengiz.animeapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding1
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding2
import com.selincengiz.animeapp.presentation.onboarding.Page
import com.selincengiz.animeapp.presentation.onboarding.pages
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(modifier = modifier) {
        Image(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = MediumPadding2).align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small),
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.description,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = MediumPadding2).align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingPagePreview() {
    AnimeAppTheme {
        OnBoardingPage(page = pages[0])
    }
}
