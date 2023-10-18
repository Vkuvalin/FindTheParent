package com.kuvalin.findtheparent.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kuvalin.findtheparent.R
import kotlin.random.Random

// Что здесь осталось доделать:
/*
1. Подачу сюда коллекции
2. При выборе 2-х одинаковых >> невидимые
2.1 При выборе 2-х неодинаковых >> закрываются
3. При выборе папки или мамки >> следующий экран
*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindParentsGameScreen(
    cardList: List<Int>,
    onStopGameClick: () -> Unit
) {

    val rotateColor = rememberInfiniteTransition(label = "")
    val rotateColorAnimation by rotateColor.animateFloat(
        initialValue = -360f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val rotateColor2 = rememberInfiniteTransition(label = "")
    val rotateColorAnimation2 by rotateColor2.animateFloat(
        initialValue = 360f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column {
        TopAppBar(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Magenta),
                        start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
                        end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
                        tileMode = TileMode.Mirror,
                    ),
                    alpha = 0.1f
                ),
            title = {
                Text(text = "Главное меню")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent), // Убирает родной цвет
            navigationIcon = {
                IconButton(onClick = { onStopGameClick() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Magenta),
                        start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
                        end = Offset(
                            rotateColorAnimation2.dp.toPx(),
                            -rotateColorAnimation.dp.toPx()
                        ),
                        tileMode = TileMode.Mirror,
                    ),
                    alpha = 0.1f
                )
        ){
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(cardList.size) {
                    Column(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FlipCard(cardList[it])
                    }
                }
            }
        }
    }
}


//region FlipCard
@Composable
fun FlipCard(photoId: Int) {

    var isFrontCardVisible by remember { mutableStateOf(true) }
    val flipCard: () -> Unit = { isFrontCardVisible = !isFrontCardVisible }

    val rotationAngle by animateFloatAsState(
        targetValue = if (isFrontCardVisible) 0f else 180f, label = ""
    )

    Box(
        modifier = Modifier
            .clickable(onClick = flipCard)
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    rotationY = rotationAngle,
                    transformOrigin = TransformOrigin.Center
                )
        ) {
            if (isFrontCardVisible) {
                FrontCard()
            } else {
                BackCard(photoId)
            }
        }
    }

}

@Composable
fun FrontCard() {
    Box(
        modifier = Modifier
            .background(brush = Brush.linearGradient(listOf(Color.Cyan, Color.Magenta)) , alpha = 0f)
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.question),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun BackCard(photoId: Int) {
    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = photoId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(8.dp)
        )
    }
}
//endregion