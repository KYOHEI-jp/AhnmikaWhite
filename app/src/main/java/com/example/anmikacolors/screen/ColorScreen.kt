package com.example.anmikacolors.screen

import android.util.Log
import android.widget.Button
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ColorScreen() {
    // 30種類の異なる濃度の白色を生成
    val originalShadesOfWhite = List(30) { i ->
        val shade = 1f - (i / 29f * 0.3f) // 最大30%までの濃度変化
        Color(shade, shade, shade)
    }

    // 色のリストを状態として保持
    var shadesOfWhite by remember { mutableStateOf(originalShadesOfWhite) }

    // 色をシャッフルする関数
    fun shuffleColors() {
        shadesOfWhite = shadesOfWhite.shuffled()
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
            .border(1.dp, Color.Gray) // 罫線を追加
            .background(color)
    )
}

@Composable
fun StartButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("スタート")
    }
}

@Composable
fun ColorBox(color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp) // グリッドのセル間のスペースを調整
            .border(1.dp, Color.Black) // 罫線を追加
            .background(color)
    )
}
