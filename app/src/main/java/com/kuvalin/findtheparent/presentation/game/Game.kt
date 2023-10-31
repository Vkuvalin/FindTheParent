package com.kuvalin.findtheparent.presentation.game

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Card.Companion.UNDEFINED_ID
import com.kuvalin.findtheparent.generals.OnBackPressButton
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import com.kuvalin.findtheparent.presentation.welcome.toPx
import com.kuvalin.findtheparent.ui.theme.ParentBlue
import com.kuvalin.findtheparent.ui.theme.ParentRed

// Что здесь осталось доделать:
/*
1. Подачу сюда коллекции
2. При выборе 2-х одинаковых >> невидимые
2.1 При выборе 2-х неодинаковых >> закрываются
3. При выборе папки или мамки >> следующий экран (подготовлен, но не настроен)
*/


@Composable
fun Game(
    cardList: List<Card>,
    gameSettingsState: GameSettingsState,
    onBackPress: () -> Unit
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
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(gameSettingsState.column),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(cardList.size) {
                    Column(
                        modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        var firstCard: Card
//                        var secondCard: Card
                        /* Идея такая:
                        TODO
                        Я выше делаю 2 ремембера на List<Int> и как-то их сверяю во FlipCard, причем
                        мне дальше нужно отреагировать сразу 2 FlipCard. В принципе это должно получиться.
                        + Мне нужно в Card добавить поле visible: Boolean
                        */
                        FlipCard(cardList[it].id, cardList[it].imageUri, cardList[it].resourceId, gameSettingsState.cardSize)
                    }
                }
            }
        }
    }
    OnBackPressButton(onBackPress)

    when (gameSettingsState) {
        is GameSettingsState.Special -> {
            WinDialog(rotateColorAnimation, rotateColorAnimation2)
        }

        else -> {}
    }
}


//region WinDialog
@OptIn(ExperimentalTextApi::class)
@Composable
fun WinDialog(rotateColorAnimation: Float, rotateColorAnimation2: Float) {

    val textBrush = Brush.linearGradient(
        colors = listOf(Color.Cyan, Color.Magenta),
        start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
        end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
        tileMode = TileMode.Mirror,
    )

    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
        },
        title = {
            WinnerParentText(textBrush)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.GameScore),
                    fontWeight = FontWeight.W500,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "МАМКА",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = ParentRed
                        )
                        Text(
                            text = "7",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = ParentRed
                        )
                    }
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ПАПКА",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = ParentBlue
                        )
                        Text(
                            text = "5",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = ParentBlue
                        )
                    }
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .clickable(
                            onClick = { AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu) },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    text = "ЗАНОВО",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500,
                    style = TextStyle(brush = textBrush)
                )
                Text(
                    modifier = Modifier
                        .clickable(
                            onClick = { AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu) },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    text = "МЕНЮ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500,
                    style = TextStyle(brush = textBrush)
                )
            }
        }
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun WinnerParentText(textBrush: Brush) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ПОБЕДИТЕЛЬ ",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            style = TextStyle(brush = textBrush)
        )
        Text(
            text = "МАМКА",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = ParentRed
        )
        Text(
            text = "!",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            style = TextStyle(brush = textBrush)
        )
    }
}
//endregion


// UNDEFINED_ID-1 - чтобы избежать совпадения с реальным id из базы // TODO
var selectedList = mutableListOf(
    mutableListOf(UNDEFINED_ID-1, UNDEFINED_ID-1), // scope
    mutableListOf(UNDEFINED_ID-1, UNDEFINED_ID-1)  // resId
)



//region FlipCard
@Composable
fun FlipCard(id: Int, uri: Uri?, photoId: Int, cardSize: Dp) {

    var isFrontCardVisible by remember { mutableStateOf(true) }
    val flipCard: () -> Unit = {
        isFrontCardVisible = !isFrontCardVisible
    }

    val checkingSelection: () -> Unit = { // TODO

        if (isFrontCardVisible) {
            flipCard()
        }

        /*
        Идея такая:
            запустить эту функцию в корутине, сделав её в вечном цикле while с тригером на
            массив selectedList
        */


        for (i in selectedList.indices)
            if(selectedList[0][i] == 0){
                selectedList[0].set(i, id)
                selectedList[1].set(i, photoId)
                break
            }

        Log.d("SELECT_CARD", "id3 -> $selectedList")

        if(selectedList[0][0] == selectedList[0][1] && selectedList[1][0] != selectedList[1][1]){
            flipCard()
            selectedList[0][0] = 0
            selectedList[0][1] = 0
            selectedList[1][0] = 0
            selectedList[1][1] = 0
            Log.d("SELECT_CARD", "CHECK-1")
        } else if (selectedList[0][0] != selectedList[0][1] && selectedList[0][0] != 0 && selectedList[0][1] != 0 ){
            flipCard()
            selectedList[0][0] = 0
            selectedList[0][1] = 0
            selectedList[1][0] = 0
            selectedList[1][1] = 0
            Log.d("SELECT_CARD", "CHECK-2")
        } else if (selectedList[0][0] == selectedList[0][1] && selectedList[1][0] == selectedList[1][1]){
            flipCard()
            selectedList[0][0] = 0
            selectedList[0][1] = 0
            selectedList[1][0] = 0
            selectedList[1][1] = 0
            Log.d("SELECT_CARD", "CHECK-3")
        }

        Log.d("SELECT_CARD", "id3 -> $selectedList")

    }




    val rotationAngle by animateFloatAsState(
        targetValue = if (isFrontCardVisible) 0f else 180f, label = ""
    )

    Box(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    rotationY = rotationAngle,
                    transformOrigin = TransformOrigin.Center
                )
        ) {
            if (isFrontCardVisible) {
                FrontCard(cardSize, checkingSelection)
            } else {
                BackCard(uri, photoId, cardSize, checkingSelection)
            }
        }
    }

}

@Composable
fun FrontCard(cardSize: Dp, checkingSelection: () -> Unit) {
    Box(
        modifier = Modifier
            .background(brush = Brush.linearGradient(listOf(Color.Cyan, Color.Magenta)), alpha = 0f)
            .size(cardSize)
            .clickable(onClick = checkingSelection)
        ,
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
fun BackCard(uri: Uri?, photoId: Int, cardSize: Dp, checkingSelection: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = checkingSelection)
            .size(cardSize),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if(uri != null) rememberImagePainter(data = uri) else painterResource(photoId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(8.dp)
        )
    }
}
//endregion

//region Backup
//TopAppBar(
//            modifier = Modifier
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(Color.Cyan, Color.Magenta),
//                        start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
//                        end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
//                        tileMode = TileMode.Mirror,
//                    ),
//                    alpha = 0.1f
//                ),
//            title = {
//                Text(text = "Главное меню")
//            },
//            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent), // Убирает родной цвет
//            navigationIcon = {
//                IconButton(onClick = { onStopGameClick() }) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = null
//                    )
//                }
//            }
//        )
//endregion

