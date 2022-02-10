package com.dhimandasgupta.ui.news.common

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhimandasgupta.common.news.remote.entity.NewsSource
import com.dhimandasgupta.theme.AppTheme

val SourceSaver = run {
    val sourceNameKey = "sourceName"
    mapSaver(
        save = { mapOf(sourceNameKey to it.sourceName) },
        restore = { NewsSource(it[sourceNameKey] as String) }
    )
}

@Composable
fun SelectableSourceItems(
    sources: Set<NewsSource>,
    selectedItemIndex: Int = 0,
    onSourceSelected: (Int) -> Unit = {}
) {
    val lazyListState by remember { mutableStateOf(LazyListState()) }

    LaunchedEffect(key1 = selectedItemIndex) {
        lazyListState.animateScrollToItem(index = selectedItemIndex)
    }

    LazyRow(
        modifier = Modifier.heightIn(48.dp, 56.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = lazyListState
    ) {
        items(
            count = sources.size,
            key = { index -> sources.elementAt(index = index).hashCode() },
            itemContent = { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val source = sources.elementAt(index = index)

                    Spacer(modifier = Modifier.width(4.dp))
                    SelectableSourceItem(
                        sourceName = source.sourceName,
                        isSelected = selectedItemIndex == sources.indexOf(source),
                        onSourceSelected = { onSourceSelected(sources.indexOf(source)) }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                if (index == sources.size - 1) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview
@Composable
fun SourceItemsSelectedPreview() {
    AppTheme(isDarkTheme = true) {
        SelectableSourceItems(
            sources = setOf(
                NewsSource("Google"),
                NewsSource("Apple"),
                NewsSource("Facebook"),
                NewsSource("Netflix"),
                NewsSource("Tesla"),
                NewsSource("News")
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview
@Composable
fun SourceItemsUnselectedPreview() {
    AppTheme(isDarkTheme = false) {
        SelectableSourceItems(
            sources = setOf(
                NewsSource("Google"),
                NewsSource("Apple"),
                NewsSource("Facebook"),
                NewsSource("Netflix"),
                NewsSource("Tesla"),
                NewsSource("News")
            )
        )
    }
}

@Composable
private fun SelectableSourceItem(
    sourceName: String,
    isSelected: Boolean = false,
    onSourceSelected: (String) -> Unit = {}
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colorScheme.primary else colorScheme.secondary,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val borderWidth by animateDpAsState(
        targetValue = if (isSelected) 4.dp else 2.dp,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    val cornerSize by animateDpAsState(
        targetValue = if (isSelected) 16.dp else 8.dp,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )

    Text(
        text = sourceName.capitalize(Locale.current),
        style = if (isSelected) typography.labelLarge else typography.labelMedium,
        color = colorScheme.onBackground,
        modifier = Modifier
            .wrapContentSize(
                align = Alignment.Center,
                unbounded = true
            )
            .clickable { onSourceSelected(sourceName) }
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(cornerSize)
            )
            .clip(RoundedCornerShape(cornerSize))
            .background(color = colorScheme.background)
            .padding(
                horizontal = cornerSize,
                vertical = cornerSize / 2
            ),
        textAlign = TextAlign.Center
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview
@Composable
fun SourceItemSelectedPreview() {
    AppTheme(isDarkTheme = true) {
        SelectableSourceItem(sourceName = "Google", isSelected = true)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview
@Composable
fun SourceItemUnselectedPreview() {
    AppTheme(isDarkTheme = false) {
        SelectableSourceItem(sourceName = "Google", isSelected = false)
    }
}