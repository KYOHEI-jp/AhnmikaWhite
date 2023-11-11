package com.example.anmikacolors.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorScreen() {
    // 30種類の異なる濃度の白色を生成
    val originalShadesOfWhite = List(30) { i ->
        val shade = 1f - (i / 29f * 0.3f)
        Color(shade, shade, shade)
    }

    var shadesOfWhite by remember { mutableStateOf(originalShadesOfWhite) }
    var selectedColor by remember { mutableStateOf(Color.White) }
    var showStartButton by remember { mutableStateOf(true) }
    var attempts by remember { mutableStateOf(0) }
    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    // クイズをリセットする関数
    fun resetQuiz() {
        showStartButton = true
        attempts = 0
        shadesOfWhite = originalShadesOfWhite.shuffled() // グリッドの色をシャッフル
        selectedColor = shadesOfWhite.random()
    }

    // 色を選択する関数
    fun selectColor(color: Color) {
        if (attempts < 5) {
            attempts++
            if (color == selectedColor) {
                alertMessage = "正解！"
            } else {
                alertMessage = if (attempts >= 5) "失敗！" else "不正解..."
            }
            showAlert = true
            if (attempts >= 5) {
                resetQuiz()
            }
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("結果") },
            text = { Text(alertMessage) },
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text("OK")
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (showStartButton) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    showStartButton = false
                    resetQuiz()
                }) {
                    Text("スタート")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(selectedColor)
                .border(1.dp, Color.Gray)
                .padding(8.dp)
        )

        // 現在の試行回数と最大試行回数を表示
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("$attempts/5")
        }

        LazyVerticalGrid(
            GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(shadesOfWhite) { color ->
                WhiteBox(color = color, onClick = { selectColor(color) })
            }
        }
    }
}

@Composable
fun WhiteBox(color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .border(1.dp, Color.Gray)
            .background(color)
            .clickable(onClick = onClick)
    )
}
