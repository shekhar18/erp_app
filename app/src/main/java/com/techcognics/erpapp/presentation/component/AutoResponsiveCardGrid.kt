package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AutoResponsiveCardGrid( cards: List<@Composable () -> Unit>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp), // Automatically fit cards
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        items(cards.size) { index ->
            cards[index]()
        }
    }
}