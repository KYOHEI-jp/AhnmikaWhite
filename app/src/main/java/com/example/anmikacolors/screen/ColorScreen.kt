package com.example.anmikacolors.screen
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.anmikacolors.composable.WhiteBox
import kotlinx.coroutines.delay

@Composable
fun ColorScreen() {
    val context = LocalContext.current
    val originalShadesOfWhite = List(30) { i ->
        val shade = 1f - (i / 29f * 0.3f)
        Color(shade, shade, shade)
    }

    var shadesOfWhite by remember { mutableStateOf(originalShadesOfWhite) }
    var selectedColor by remember { mutableStateOf(Color.White) }
    var showStartButton by remember { mutableStateOf(true) }
    var attempts by remember { mutableStateOf(0) }
    var toastMessage by remember { mutableStateOf("") }
    var showAnswer by remember { mutableStateOf(false) }

    // クイズをリセットする関数
    fun resetQuiz() {
        showStartButton = true
        attempts = 0
        showAnswer = false
        shadesOfWhite = originalShadesOfWhite.shuffled()
        selectedColor = shadesOfWhite.random()
    }

    // 色を選択する関数
    fun selectColor(color: Color) {
        if (attempts < 5) {
            attempts++
            if (color == selectedColor) {
                toastMessage = "正解！"
                resetQuiz()
            } else {
                if (attempts >= 5) {
                    showAnswer = true
                    toastMessage = "失敗！"
                    resetQuiz()
                } else {
                    toastMessage = "不正解..."
                }
            }
        }
    }

    // Toastを表示
    LaunchedEffect(toastMessage) {
        if (toastMessage.isNotEmpty()) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            delay(2000) // Toast表示時間
            toastMessage = "" // Toastメッセージをリセット
        }
    }

    // UIのレイアウト
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (showStartButton) {
                Button(onClick = {
                    showStartButton = false
                    resetQuiz()
                }) {
                    Text("スタート")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { resetQuiz() }) {
                Text("リセット")
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
                WhiteBox(color = color, onClick = { selectColor(color) }, showAnswer = showAnswer && color == selectedColor)
            }
        }
    }
}