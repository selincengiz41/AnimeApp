package com.selincengiz.animeapp.presentation.common


import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.ui.theme.AnimeAppTheme
import com.selincengiz.animeapp.ui.theme.BlueButtonColor
import com.selincengiz.animeapp.ui.theme.PurpleButtonColor

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    glowingColor: Color,
    containerColor: Color = Color.White.copy(0f),
    cornersRadius: Dp = 20.dp,
    glowingRadius: Dp = 20.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f, // Left
                        0f, // Top
                        canvasSize.width, // Right
                        canvasSize.height, // Bottom
                        cornersRadius.toPx(), // Radius X
                        cornersRadius.toPx(), // Radius Y
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.2f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        Box(modifier = modifier) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .searchBarBorder()
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(0.9f),
                        shape = MaterialTheme.shapes.large
                    ),
                value = text,
                onValueChange = onValueChange,
                readOnly = readOnly,
                placeholder = {
                    Text(
                        text = "Search for a content",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(
                            id = R.color.placeholder
                        )
                    )
                },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = containerColor,
                    focusedContainerColor = containerColor,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                    }
                ),
                textStyle = MaterialTheme.typography.bodySmall,
                interactionSource = interactionSource,

                )
        }
    }
}

fun Modifier.searchBarBorder(): Modifier = composed {
    this.border(
        width = 2.dp,
        brush = Brush.horizontalGradient(
            colors = listOf(BlueButtonColor, PurpleButtonColor)
        ),
        shape = MaterialTheme.shapes.large
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchBarPreview() {
    AnimeAppTheme {
        CustomSearchBar(
            modifier = Modifier.padding(10.dp),
            text = "",
            readOnly = false,
            onValueChange = {},
            glowingColor = BlueButtonColor
        ) {

        }
    }
}