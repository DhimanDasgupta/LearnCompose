package com.dhimandasgupta.ui.news.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.dhimandasgupta.commom.android.network.ConnectionState
import com.dhimandasgupta.commom.android.openBrowserScreen
import com.dhimandasgupta.commom.android.openInternetSettings
import com.dhimandasgupta.common.composables.NoNetwork
import com.dhimandasgupta.common.news.remote.NewsApiService
import com.dhimandasgupta.common.news.remote.entity.NewsDataState
import com.dhimandasgupta.common.news.remote.entity.NewsSource
import com.dhimandasgupta.common.state.connectivityState
import com.dhimandasgupta.size.WindowSize
import com.dhimandasgupta.theme.AppTheme
import com.dhimandasgupta.ui.news.common.RenderErrorState
import com.dhimandasgupta.ui.news.common.RenderLoadingState
import com.dhimandasgupta.ui.news.common.RenderSuccessState
import com.dhimandasgupta.ui.news.common.SelectableSourceItems
import com.dhimandasgupta.ui.news.common.SourceSaver
import com.google.accompanist.insets.ProvideWindowInsets

private val sources = setOf(
    NewsSource("asia"),
    NewsSource("africa"),
    NewsSource("europe"),
    NewsSource("north america"),
    NewsSource("south america"),
    NewsSource("google"),
    NewsSource("apple"),
    NewsSource("facebook"),
    NewsSource("netflix"),
    NewsSource("tesla"),
    NewsSource("amazon"),
    NewsSource("Bitcoinist"),
    NewsSource("newsBTC"),
    NewsSource("CNN"),
    NewsSource("Reuters"),
    NewsSource("CBS17.com"),
    NewsSource("Engadget"),
    NewsSource("Google News"),
    NewsSource("BBC"),
    NewsSource("Electrek"),
    NewsSource("KOB"),
    NewsSource("Tesla"),
    NewsSource("News"),
)

@Composable
fun NewsScreen(
    windowSize: WindowSize
) {
    ProvideWindowInsets {
        AppTheme {
            /**
             * To demonstrate DisposableEffect
             *
             * Keeping the screen on until the NewsScreen is showing
             * */
            val currentView = LocalView.current
            DisposableEffect(key1 = Unit) {
                currentView.keepScreenOn = true

                onDispose {
                    currentView.keepScreenOn = false
                }
            }

            // API for the rest call
            val newsApiService = remember { NewsApiService.create() }

            // Selected index
            var selectedIndex by rememberSaveable { mutableStateOf(0) }

            // Selected NewsSource
            var source by rememberSaveable(stateSaver = SourceSaver) {
                mutableStateOf(sources.elementAt(selectedIndex))
            }

            // When NewsSource is selected from the set
            val onSourceSelected: (Int) -> Unit = {
                selectedIndex = it
                source = sources.elementAt(it)
            }

            // When Clicked on a News Article
            val context = LocalContext.current
            val onNewsClicked: (String?) -> Unit = { url ->
                openBrowserScreen(
                    context = context,
                    url = url
                )
            }
            val openInternetSettings = { openInternetSettings(context = context) }

            Column {
                val connection by connectivityState()

                AnimatedVisibility(
                    visible = connection == ConnectionState.Available
                ) {
                    SelectableSourceItems(
                        sources = sources,
                        selectedItemIndex = selectedIndex,
                        onSourceSelected = onSourceSelected
                    )
                }

                AnimatedVisibility(
                    visible = connection == ConnectionState.Unavailable
                ) {
                    NoNetwork(
                        windowSize = windowSize,
                        openInternetSettings = openInternetSettings
                    )
                }

                RenderNewsStateWithLaunchedEffect(
                    windowSize = windowSize,
                    newsApiService = newsApiService,
                    source = source,
                    onNewsClicked = onNewsClicked
                )
            }
        }
    }
}

/**
 * Way II --- Using LaunchedEffect
 *
 * Approach two to load data - no state is given to you, you need to create and manage the state
 *
 * Good fit for this example, to do something when the key1 is changed
 *
 * Point to remember - Composable function can not be called from inside
 *
 * Coroutine Scope is crated automatically, so no need to cancel the job
 * */
@Composable
private fun RenderNewsStateWithLaunchedEffect(
    windowSize: WindowSize,
    newsApiService: NewsApiService,
    source: NewsSource,
    onNewsClicked: (String?) -> Unit = {}
) {
    val newsDataState: MutableState<NewsDataState> = remember {
        mutableStateOf(NewsDataState.LoadingState(source = source))
    }

    LaunchedEffect(key1 = source) {
        newsDataState.value = NewsDataState.LoadingState(source = source)
        val response = newsApiService.getNews(source.sourceName)

        newsDataState.value = when {
            response.isSuccess -> response
                .getOrNull()?.let { NewsDataState.SuccessState(it) }
                ?: NewsDataState.ErrorState(Exception("Hmm, something is wrong..."))
            response.isFailure -> response
                .exceptionOrNull()?.let { NewsDataState.ErrorState(it) }
                ?: NewsDataState.ErrorState(Exception("Hmm, something is wrong..."))
            else -> NewsDataState.LoadingState(source = source)
        }
    }
    Crossfade(
        targetState = newsDataState.value,
        modifier = Modifier.fillMaxSize(),
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    ) {
        RenderNewsState(
            source = source,
            newsDataState = it,
            windowSize = windowSize,
            onNewsClicked = onNewsClicked
        )
    }
}

@Composable
private fun RenderNewsState(
    windowSize: WindowSize,
    source: NewsSource,
    newsDataState: NewsDataState,
    onNewsClicked: (String?) -> Unit = {}
) {
    when (newsDataState) {
        is NewsDataState.LoadingState -> RenderLoadingState(source = source)
        is NewsDataState.SuccessState -> if (newsDataState.newsResponse.articles.isNotEmpty())
            RenderSuccessState(
                windowSize = windowSize,
                newsResponse = newsDataState.newsResponse,
                onNewsClicked = onNewsClicked
            )
        else RenderErrorState(
            source = source,
            throwable = Exception("Sorry, no data available for this source")
        )
        is NewsDataState.ErrorState -> RenderErrorState(
            source = source,
            throwable = newsDataState.throwable
        )
    }
}
