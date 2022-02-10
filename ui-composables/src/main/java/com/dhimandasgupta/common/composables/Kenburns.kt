package com.dhimandasgupta.common.composables

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.dhimandasgupta.theme.AppTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay
import kotlin.random.Random

// For Testing and Preview purpose
private const val IMAGE_URL =
    "https://cdn.britannica.com/78/69678-050-491A5ED8/Bedroom-oil-canvas-Vincent-van-Gogh-Art-1889.jpg"
private const val IMAGE_URL_1 =
    "https://cdn.britannica.com/33/189333-050-9658C18D/Vincent-van-Gogh-The-Night-Cafe-1888-Yale-University-Art-Museum.jpg"
private const val IMAGE_URL_2 =
    "https://cdn.britannica.com/78/43678-050-F4DC8D93/Starry-Night-canvas-Vincent-van-Gogh-New-1889.jpg"
private const val IMAGE_URL_3 =
    "https://cdn.britannica.com/41/181941-050-DBEBA6D5/Old-Tower-canvas-oil-cardboard-Fields-Vincent-1884.jpg"
private const val IMAGE_URL_4 =
    "https://cdn.britannica.com/57/211157-050-06EC285B/Vincent-van-Gogh-The-Potato-Eaters-1885-Van-Gogh-Museum-Amsterdam.jpg"
private const val IMAGE_URL_5 =
    "https://cdn.britannica.com/51/159751-050-ABF8B2C5/Fishing-Boats-on-the-Beach-oil-canvas-1888.jpg"

private const val CENTER = 0.5f

@OptIn(ExperimentalCoilApi::class)
@Composable
fun KenBurnsImage(
    requestedSize: Int,
    scaleFactorInitial: Float,
    scaleFactorTarget: Float,
    url: String,
    contentDescription: String?,
    modifier: Modifier,
) {
    val durationMillis = 10000
    val initialDelayMillis = 0
    val animation = tween<Float>(
        durationMillis = durationMillis,
        delayMillis = initialDelayMillis
    )
    val animationSpec = infiniteRepeatable(
        animation = animation,
        repeatMode = RepeatMode.Reverse
    )

    val scaleXInfiniteTransition = rememberInfiniteTransition()
    val scaleX by scaleXInfiniteTransition.animateFloat(
        scaleFactorInitial,
        scaleFactorTarget,
        animationSpec = animationSpec
    )

    val scaleYInfiniteTransition = rememberInfiniteTransition()
    val scaleY by scaleYInfiniteTransition.animateFloat(
        scaleFactorInitial,
        scaleFactorTarget,
        animationSpec = animationSpec
    )

    var offsetXPair by remember { mutableStateOf(Pair(0f, 0f)) }
    var offsetYPair by remember { mutableStateOf(Pair(0f, 0f)) }

    val offsetXInfiniteTransition = rememberInfiniteTransition()
    val offsetX by offsetXInfiniteTransition.animateFloat(
        offsetXPair.first,
        offsetXPair.second,
        animationSpec = animationSpec
    )

    val offsetYInfiniteTransition = rememberInfiniteTransition()
    val offsetY by offsetYInfiniteTransition.animateFloat(
        offsetYPair.first,
        offsetYPair.second,
        animationSpec = animationSpec
    )

    LaunchedEffect(key1 = offsetXPair, key2 = offsetYPair) {
        delay(durationMillis.toLong())

        offsetXPair = Pair(
            offsetXPair.second,
            generateRandomFloatInRange(0.35f, 0.65f)
        )

        offsetYPair = Pair(
            offsetYPair.second,
            generateRandomFloatInRange(0.45f, 0.55f)
        )
    }

    val painter = rememberImagePainter(
        data = url,
        builder = {
            size(requestedSize)
        }
    )

    Box(
        modifier = Modifier.then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scaleX,
                    scaleY = scaleY,
                    transformOrigin = TransformOrigin(
                        pivotFractionX = offsetX,
                        pivotFractionY = offsetY
                    )
                )
                .placeholder(
                    visible = painter.state !is ImagePainter.State.Success,
                    color = MaterialTheme.colorScheme.background,
                    highlight = PlaceholderHighlight.shimmer(),
                ),
            contentScale = ContentScale.Crop
        )
    }
}

private fun generateRandomFloatInRange(
    min: Float,
    max: Float
) = (
    CENTER +
        if (Random.nextBoolean()) Random.nextFloat() * (max - min)
        else Random.nextFloat() * (max - min) * -1f)
    .coerceIn(min, max)

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview
@Composable
private fun KenBurnsImagePreview() {
    AppTheme(isDarkTheme = false) {
        KenBurnsImage(
            requestedSize = 100,
            url = IMAGE_URL,
            contentDescription = "Some random description",
            modifier = Modifier
                .size(128.dp),
            scaleFactorInitial = 1.25f,
            scaleFactorTarget = 1.75f,
        )
    }
}