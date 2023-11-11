package com.example.anmikacolors.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ColorScreen() {
    // 30種類の異なる濃度の白色を生成
    val originalShadesOfWhite = List(30) { i ->
        val shade = 1f - (i / 29f * 0.3f)
        Color(shade, shade, shade)
    }

    // 色のリストを状態として保持
    var shadesOfWhite by remember { mutableStateOf(originalShadesOfWhite) }

    // 選ばれた色を状態として保持
    var selectedColor by remember { mutableStateOf(Color.White) }

    // 色をシャッフルする関数
    fun shuffleColors() {
        shadesOfWhite = originalShadesOfWhite.shuffled()
        selectedColor = shadesOfWhite.random() // ランダムに選ばれた色を選択
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 選ばれた色を表示するBox
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(selectedColor)
                .padding(8.dp)
                .border(1.dp, Color.Gray)
        )

        LazyVerticalGrid(
            GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(shadesOfWhite) { color ->
                WhiteBox(color = color)
            }
        }

        // スタートボタン
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            StartButton(onClick = { shuffleColors() })
        }
    }
}

@Composable
fun WhiteBox(color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .border(1.dp, Color.Gray)
            .background(color)
    )
}

@Composable
fun StartButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("スタート")
    }
}
