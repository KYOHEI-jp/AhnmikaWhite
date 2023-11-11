package com.example.anmikacolors.screen

import android.util.Log
import android.widget.Button
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text


@Composable
fun ColorScreen() {
    Column {
        // 色のリスト
        val colors = listOf(Color.Red, Color.Green, Color.Blue) // 例

        // 各色に対して ColorItem を表示
        colors.forEach { color ->
            ColorItem(color = color)
        }

    }
}


@Composable
fun ColorItem(color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
            .padding(8.dp)
    )
}