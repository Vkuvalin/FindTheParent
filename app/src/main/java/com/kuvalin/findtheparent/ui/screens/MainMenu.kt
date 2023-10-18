package com.kuvalin.findtheparent.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.ui.theme.ParentBlue
import com.kuvalin.findtheparent.ui.theme.ParentRed



// На тему удаления тени при нажатии и тп
// https://stackoverflow.com/questions/69076711/how-to-set-gradient-background-in-topappbar-using-jetpack-compose

@Composable
fun FindParentMainScreen(
    onStartGameClick: () -> Unit
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Magenta),
                    start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
                    end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
                    tileMode = TileMode.Mirror,
                ),
                alpha = 0.1f
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        ParentsTablo(rotateColorAnimation, rotateColorAnimation2)
        Score()

        Spacer(modifier = Modifier)
        Spacer(modifier = Modifier)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Magenta),
                    start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
                    end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
                    tileMode = TileMode.Mirror,
                ),
                alpha = 1f
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0F)
            ),
            onClick = { onStartGameClick() }
        ) {
            Text(text = "НАЧАТЬ!")
        }

    }
}



@Composable
fun ParentsTablo(animationVector: Float, animationVector2: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParentCard("МАМКА", R.drawable.mama, ParentRed, animationVector, animationVector2)
        }

        Column(
            modifier = Modifier
                .weight(1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParentCard("ПАПКА", R.drawable.papa, ParentBlue, animationVector, animationVector2)
        }
    }
}


@Composable
fun ParentCard(name: String, id: Int, color: Color, animationVector: Float, animationVector2: Float) {
    Text(
        modifier = Modifier
            .padding(bottom = 16.dp),
        text = "$name",
        fontWeight = W500,
        color = color,
        fontSize = 32.sp
    )
    Box(
        modifier = Modifier
            .size(150.dp)
//            .border(width = 5.dp, color = color),
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(8.dp)
        )
    }
    Button(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(
                brush = Brush.linearGradient(
                colors = listOf(Color.Cyan, Color.Magenta),
                start = Offset(0.dp.toPx(), animationVector.dp.toPx()),
                end = Offset(animationVector2.dp.toPx(), -animationVector.dp.toPx()),
                tileMode = TileMode.Mirror,
                ),
                alpha = 1f
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0F)
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(text = "добавить фото")
    }
}


@Composable
fun Score() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Счёт:",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "7",
                fontSize = 32.sp,
                fontWeight = W500,
                color = ParentRed
            )
            Spacer(modifier = Modifier.weight(3f))
            Text(
                text = "5",
                fontSize = 32.sp,
                fontWeight = W500,
                color = ParentBlue
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}