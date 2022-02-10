package com.dhimandasgupta.common.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhimandasgupta.size.WindowSize
import com.dhimandasgupta.theme.AppTheme

@Composable
fun NoNetwork(
    windowSize: WindowSize,
    openInternetSettings: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorScheme.background)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "You are not connected",
            style = when (windowSize) {
                WindowSize.Expanded -> typography.bodyLarge
                WindowSize.Medium -> typography.bodyMedium
                WindowSize.Compact -> typography.bodySmall
            },
            color = colorScheme.onBackground,
            modifier = Modifier
                .wrapContentSize(
                    align = Alignment.Center,
                    unbounded = true
                )
                .background(color = colorScheme.background),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Go to settings",
            style = when (windowSize) {
                WindowSize.Expanded -> typography.labelLarge
                WindowSize.Medium -> typography.labelMedium
                WindowSize.Compact -> typography.labelSmall
            }.copy(
                textDecoration = TextDecoration.Underline
            ),
            color = colorScheme.onBackground,
            modifier = Modifier
                .wrapContentSize(
                    align = Alignment.Center,
                    unbounded = true
                )
                .background(color = colorScheme.background)
                .clickable {
                    if (openInternetSettings != null) {
                        openInternetSettings()
                    }
                },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoNetworkDarkPreview() {
    AppTheme(isDarkTheme = true) {
        NoNetwork(windowSize = WindowSize.Compact)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NoNetworkLightPreview() {
    AppTheme(isDarkTheme = false) {
        NoNetwork(windowSize = WindowSize.Compact)
    }
}