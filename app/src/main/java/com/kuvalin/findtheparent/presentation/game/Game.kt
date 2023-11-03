package com.kuvalin.findtheparent.presentation.game

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
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
import coil.compose.rememberAsyncImagePainter
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.entity.Score
import com.kuvalin.findtheparent.domain.usecase.AddGameScoreUseCase
import com.kuvalin.findtheparent.domain.usecase.GetGameScoreUseCase
import com.kuvalin.findtheparent.generals.OnBackPressButton
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.presentation.gamesettings.GameSettingsState
import com.kuvalin.findtheparent.presentation.welcome.toPx
import com.kuvalin.findtheparent.ui.theme.ParentBlue
import com.kuvalin.findtheparent.ui.theme.ParentRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val MAMA_ID = 777
const val PAPA_ID = 888
var scoreMama = 0
var scorePapa = 0


@Composable
fun Game(
    repository: CardListRepositoryImpl,
    cardList: List<Card>,
    gameSettingsState: GameSettingsState,
    onBackPress: () -> Unit,
    soundCard: MediaPlayer,
    soundWin: MediaPlayer
) {
    val scope = CoroutineScope(Dispatchers.Default)


    Column {
        //region Animation
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
        //endregion
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
                items(cardList.size) { position ->
                    Column(
                        modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FlipCard(cardList[position], gameSettingsState.cardSize, position, soundCard, scope)
                    }
                }
            }
        }
    }
    OnBackPressButton(onBackPress) { clearList() }

    if ((MAMA_ID in selectedList[2] || PAPA_ID in selectedList[2]) || (selectedList[2].size == 10)) {
        WinDialog(repository, soundWin, scope)
    }

    BackHandler {
        onBackPress()
        clearList()
    }

}


//region WinDialog
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalTextApi::class)
@Composable
fun WinDialog(
    repository: CardListRepositoryImpl,
    mMediaPlayer: MediaPlayer,
    scopeExternal: CoroutineScope
) {

    //region Animation
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
    //endregion

    val scope = CoroutineScope(Dispatchers.IO)
    var loadCompleted by remember { mutableStateOf(false) }
    var soundPlayState by remember { mutableStateOf(false) }

    if (!soundPlayState) { soundPlayState = playSound(mMediaPlayer, scopeExternal) }

    if (!loadCompleted) {
        scope.launch {
            delay(500)
            scoreMama = GetGameScoreUseCase(repository).invoke().mama
            scorePapa = GetGameScoreUseCase(repository).invoke().papa

            if (MAMA_ID in selectedList[2]){
                scoreMama++
            }else if(PAPA_ID in selectedList[2]) {
                scorePapa++
            }

            loadCompleted = true
        }
    }

    if (loadCompleted) {
        val textBrush = Brush.linearGradient(
            colors = listOf(Color.Cyan, Color.Magenta),
            start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
            end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
            tileMode = TileMode.Mirror,
        )

        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { closeScreen(scope, repository) },
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
                            .fillMaxWidth(),
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
                                text = "$scoreMama",
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
                                text = "$scorePapa",
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
                                onClick = {
                                    AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
                                    scope.launch {
                                        AddGameScoreUseCase(repository).invoke(
                                            Score(mama = scoreMama, papa = scorePapa)
                                        )
                                    }
                                    clearList()
                                },
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
                                onClick = { closeScreen(scope, repository) },
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
            text = if (MAMA_ID in selectedList[2]) "МАМКА" else "ПАПКА",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = if (MAMA_ID in selectedList[2]) ParentRed else ParentBlue
        )
        Text(
            text = "!",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            style = TextStyle(brush = textBrush)
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
private fun closeScreen(
    scope: CoroutineScope,
    repository: CardListRepositoryImpl
) {
    AppNavigationScreens.putScreenState(AppNavigationScreens.MainMenu)
    scope.launch {
        AddGameScoreUseCase(repository).invoke(
            Score(mama = scoreMama, papa = scorePapa)
        )
    }
    clearList()
}

//endregion

//region selectedList
var selectedList = mutableListOf(
    mutableListOf(0, 0),  // id
    mutableListOf(0, 0),  // resId
    mutableListOf()       // winning cards
)

fun clearList() {
    selectedList = mutableListOf(
        mutableListOf(0, 0),  // id
        mutableListOf(0, 0),  // resId
        mutableListOf()       // winning cards
    )
}

suspend fun onWaitClickButton() {
    while (true) {
        if (selectedList[0][0] == 0 || selectedList[0][1] == 0) {
            delay(100)
        } else {
            break
        }
    }
}
//endregion

//region FlipCard
@Composable
fun FlipCard(card: Card, cardSize: Dp, position: Int, mMediaPlayer: MediaPlayer, scopeExternal: CoroutineScope) {

    var isFrontCardVisible by remember { mutableStateOf(true) }
    val flipCard: () -> Unit = { isFrontCardVisible = !isFrontCardVisible }
    val scope = CoroutineScope(Dispatchers.Default)

    val checkingSelection: () -> Unit = {

        scope.launch {
            if (isFrontCardVisible) {
                flipCard()
            }

            for (i in selectedList.indices)
                if (selectedList[0][i] == 0) {
                    selectedList[0].set(i, card.id)
                    selectedList[1].set(i, position)
                    break
                }

            onWaitClickButton()

            delay(500)
            if (selectedList[0][0] == selectedList[0][1] && selectedList[1][0] != selectedList[1][1]) {
                isFrontCardVisible = false
                if (card.id !in selectedList[2]) {
                    selectedList[2].add(card.id)
                }
            } else if (selectedList[0][0] != selectedList[0][1] && selectedList[0][0] != 0 && selectedList[0][1] != 0) {
                isFrontCardVisible = true
            } else if (selectedList[0][0] == selectedList[0][1] && selectedList[1][0] == selectedList[1][1]) {
                isFrontCardVisible = true
            }

            delay(300)
            selectedList[0][0] = 0
            selectedList[0][1] = 0
            selectedList[1][0] = 0
            selectedList[1][1] = 0
        }

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
                FrontCard(card, cardSize, checkingSelection, mMediaPlayer, scopeExternal)
            } else {
                BackCard(card, cardSize, checkingSelection)
            }
        }
    }

}

@Composable
fun FrontCard(card: Card, cardSize: Dp, checkingSelection: () -> Unit, mMediaPlayer: MediaPlayer, scope: CoroutineScope) {

    var soundPlayState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(brush = Brush.linearGradient(listOf(Color.Cyan, Color.Magenta)), alpha = 0f)
            .size(cardSize)
            .clickable(onClick = {
                if ((card.id !in selectedList[2]) && !(selectedList[0][0] != 0 && selectedList[0][1] != 0)) {
                    checkingSelection()
                    if (!soundPlayState) { soundPlayState = playSound(mMediaPlayer, scope) }
                }
            }),
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
fun BackCard(card: Card, cardSize: Dp, checkingSelection: () -> Unit) {

    Box(
        modifier = Modifier
            .clickable(onClick = {
                if ((card.id !in selectedList[2]) && !(selectedList[0][0] != 0 && selectedList[0][1] != 0)) {
                    checkingSelection()
                }
            })
            .size(cardSize),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if (card.imageUri != null) rememberAsyncImagePainter(model = card.imageUri)
            else painterResource(card.resourceId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(8.dp)
        )
    }

}
//endregion


@SuppressLint("CoroutineCreationDuringComposition")
private fun playSound(mMediaPlayer: MediaPlayer, scope: CoroutineScope): Boolean {
    return !(scope.async {mMediaPlayer.start() }.isCompleted)
}



