package com.dhimandasgupta.ui.news.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhimandasgupta.common.composables.KenBurnsImage
import com.dhimandasgupta.common.news.remote.entity.Article
import com.dhimandasgupta.common.news.remote.entity.NewsResponse
import com.dhimandasgupta.common.news.remote.entity.Source
import com.dhimandasgupta.size.WindowSize
import com.dhimandasgupta.theme.AppTheme

@Composable
internal fun RenderSuccessState(
    windowSize: WindowSize,
    newsResponse: NewsResponse,
    onNewsClicked: (String?) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            count = newsResponse.articles.size,
            key = { index -> newsResponse.articles[index].hashCode() + index },
            itemContent = { index ->
                val article = newsResponse.articles[index]
                val gradientColors = gradientColors(isSystemInDarkTheme())

                when (windowSize) {
                    WindowSize.Expanded -> ArticleMedium(
                        article = article,
                        gradientColors = gradientColors,
                        isExpandedMode = true,
                        onNewsClicked = onNewsClicked
                    )
                    WindowSize.Medium -> ArticleMedium(
                        article = article,
                        gradientColors = gradientColors,
                        onNewsClicked = onNewsClicked
                    )
                    WindowSize.Compact -> ArticleCompact(
                        article = article,
                        gradientColors = gradientColors,
                        onNewsClicked = onNewsClicked
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        )
    }
}

@Composable
private fun ArticleMedium(
    article: Article,
    gradientColors: List<Color>,
    isExpandedMode: Boolean = false,
    onNewsClicked: (String?) -> Unit = {}
) {
    val size = if (isExpandedMode) 384.dp else 256.dp

    val scaleFactorInitial = if (isExpandedMode) 1.75f else 2.5f
    val scaleFactorTarget = if (isExpandedMode) 3.5f else 4f

    val sizeInPx = with(LocalDensity.current) { size.toPx() }

    val modifier = article.urlToImage?.let {
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(size)
    } ?: Modifier
        .clip(RoundedCornerShape(16.dp))
        .fillMaxWidth()

    Card(
        modifier = modifier,
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors)
                )
                .clickable { onNewsClicked(article.url) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            article.urlToImage?.let { image ->
                KenBurnsImage(
                    requestedSize = sizeInPx.toInt(),
                    scaleFactorInitial = scaleFactorInitial,
                    scaleFactorTarget = scaleFactorTarget,
                    url = image,
                    contentDescription = article.description,
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    text = article.title ?: "N/A",
                    style = if (isExpandedMode) typography.headlineLarge else typography.headlineMedium,
                    color = colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )

                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentSize(),
                    text = article.description ?: "N/A",
                    style = if (isExpandedMode) typography.labelLarge else typography.labelMedium,
                    color = colorScheme.onBackground.copy(alpha = 0.8f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
private fun ArticleCompact(
    article: Article,
    gradientColors: List<Color>,
    onNewsClicked: (String?) -> Unit = {}
) {
    val size = 128.dp

    val sizeInPx = with(LocalDensity.current) { size.toPx() }

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .wrapContentSize()
            .clickable { onNewsClicked(article.url) },
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors)
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                article.urlToImage?.let { image ->
                    KenBurnsImage(
                        requestedSize = sizeInPx.toInt(),
                        scaleFactorInitial = 1.25f,
                        scaleFactorTarget = 1.75f,
                        url = image,
                        contentDescription = article.description,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(size)
                            .background(Color.Transparent)
                    )
                }

                Text(
                    modifier = Modifier
                        .heightIn(min = 16.dp, max = 112.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    text = article.title ?: "N/A",
                    style = typography.headlineSmall,
                    color = colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(),
                text = article.description ?: "N/A",
                style = typography.labelSmall,
                color = colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }
    }
}

private fun gradientColors(
    isSystemInDarkTheme: Boolean
): List<Color> = if (isSystemInDarkTheme) {
    listOf(
        Color.DarkGray,
        Color.DarkGray.copy(alpha = 0.9f),
        Color.DarkGray.copy(alpha = 0.8f),
        Color.DarkGray.copy(alpha = 0.7f),
        Color.DarkGray.copy(alpha = 0.8f),
        Color.DarkGray.copy(alpha = 0.9f),
        Color.DarkGray,
    )
} else {
    listOf(
        Color.LightGray,
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.4f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray,
    )
}

private val previewArticle = Article(
    author = "Jon Stone",
    title = "EU member states to sue Brussels for classifying fossil fuel gas and nuclear power as ‘green energy’",
    description = "Controversial ‘taxonomy’ has caused anger in some countries",
    url = "https://www.independent.co.uk/climate-change/news/eu-gas-nuclear-green-energy-taxonomy-b2007126.html",
    urlToImage = "https://static.independent.co.uk/2021/12/20/14/gas2.jpg?quality=75&width=1200&auto=webp",
    publishedAt = "2022-02-03T17:35:47Z",
    content = "EU member states are to take legal action against the European Commission after it decided to count natural gas and nuclear power as green energy.\\r\\nThe European Union's executive controversially incl… [+11321 chars]",
    source = Source(
        id = "independent",
        name = "Independent"
    )
)

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, widthDp = 1200)
@Composable
private fun ArticleExpandLightPreview() {
    AppTheme(isDarkTheme = false) {
        ArticleMedium(
            article = previewArticle,
            gradientColors = gradientColors(false),
            isExpandedMode = true
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 1200)
@Composable
private fun ArticleExpandDarkPreview() {
    AppTheme(isDarkTheme = true) {
        ArticleMedium(
            article = previewArticle,
            gradientColors = gradientColors(true),
            isExpandedMode = true
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, widthDp = 840)
@Composable
private fun ArticleMediumLightPreview() {
    AppTheme(isDarkTheme = false) {
        ArticleMedium(
            article = previewArticle,
            gradientColors = gradientColors(false)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 840)
@Composable
private fun ArticleMediumDarkPreview() {
    AppTheme(isDarkTheme = true) {
        ArticleMedium(
            article = previewArticle,
            gradientColors = gradientColors(true)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ArticleCompactLightPreview() {
    AppTheme(isDarkTheme = false) {
        ArticleCompact(
            article = previewArticle,
            gradientColors = gradientColors(false)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCompactDarkPreview() {
    AppTheme(isDarkTheme = true) {
        ArticleCompact(
            article = previewArticle,
            gradientColors = gradientColors(true)
        )
    }
}
