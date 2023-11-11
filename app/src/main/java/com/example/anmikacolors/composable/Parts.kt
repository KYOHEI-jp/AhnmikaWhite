package com.example.anmikacolors.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WhiteBox(color: Color, onClick: () -> Unit, showAnswer: Boolean, disabled: Boolean, failed: Boolean) {
    val boxColor = if (failed) Color.Red else color // 失敗したグリッドは赤く表示

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .border(1.dp, Color.Gray)
            .background(boxColor)
            .clickable(enabled = !disabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (showAnswer) {
            Text("正解はこれでした。", color = Color.Black)
        }
    }
}