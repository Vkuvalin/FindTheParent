package com.kuvalin.findtheparent.presentation.gamesettings

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.entity.Card
import com.kuvalin.findtheparent.domain.usecase.GetCardListUseCase
import com.kuvalin.findtheparent.generals.CardStyle
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.generals.NoRippleTheme
import com.kuvalin.findtheparent.generals.OnBackPressButton
import com.kuvalin.findtheparent.presentation.game.clearList
import com.kuvalin.findtheparent.presentation.main.MainActivity
import com.kuvalin.findtheparent.presentation.welcome.toPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

enum class ButtonType {
    INIT, EASY, MEDIUM, HARD, SPECIAL
}

var buttonType = ButtonType.INIT

suspend fun getCardList(repository: CardListRepositoryImpl, gameSettingsState: GameSettingsState): List<Card>{
    return GetCardListUseCase(repository)(
        style = when(CardStyleState.cardStyleState.value){
            is CardStyleState.Style1 -> { CardStyle.STYLE1 }
            is CardStyleState.Style2 -> { CardStyle.STYLE2 }
            is CardStyleState.Style3 -> { CardStyle.STYLE3 }
        },
        type = CardType.NORMAL,
        gameSettingsState = gameSettingsState
    )
}


@Composable
fun GameSettingsMenu(
    repository: CardListRepositoryImpl,
    onBackPress: () -> Unit
) {

    val context = LocalContext.current

    //region ColorsButtonAnimation
    // Easy
    var isFirstTranslateEasy by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesEasy by animateDpAsState(
        targetValue = if (isFirstTranslateEasy) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

    var isSecondTranslateEasy by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesEasy by animateDpAsState(
        targetValue = if (isSecondTranslateEasy) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )


    // Medium
    var isFirstTranslateMedium by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesMedium by animateDpAsState(
        targetValue = if (isFirstTranslateMedium) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

    var isSecondTranslateMedium by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesMedium by animateDpAsState(
        targetValue = if (isSecondTranslateMedium) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )


    // Hard
    var isFirstTranslateHard by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesHard by animateDpAsState(
        targetValue = if (isFirstTranslateHard) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

    var isSecondTranslateHard by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesHard by animateDpAsState(
        targetValue = if (isSecondTranslateHard) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

    // Special
    var isFirstTranslateSpecial by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesSpecial by animateDpAsState(
        targetValue = if (isFirstTranslateSpecial) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

    var isSecondTranslateSpecial by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesSpecial by animateDpAsState(
        targetValue = if (isSecondTranslateSpecial) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 1000
        ),
        label = ""
    )

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


    Column(
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
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp,
                    vertical = 0.dp
                )
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
                    alpha = 0.2f
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                ColorsButton(
                    text = "EASY",
                    firstTranslateAnimationX = firstTranslateCoordinatesEasy,
                    secondTranslateAnimationX = secondTranslateCoordinatesEasy,
                    typeButton = ButtonType.EASY
                )

                Spacer(modifier = Modifier.height(20.dp))

                ColorsButton(
                    text = "MEDIUM",
                    firstTranslateAnimationX = firstTranslateCoordinatesMedium,
                    secondTranslateAnimationX = secondTranslateCoordinatesMedium,
                    typeButton = ButtonType.MEDIUM
                )

                Spacer(modifier = Modifier.height(20.dp))

                ColorsButton(
                    text = "HARD",
                    firstTranslateAnimationX = firstTranslateCoordinatesHard,
                    secondTranslateAnimationX = secondTranslateCoordinatesHard,
                    typeButton = ButtonType.HARD
                )

                Spacer(modifier = Modifier.height(20.dp))

                ColorsButton(
                    text = "SPECIAL",
                    firstTranslateAnimationX = firstTranslateCoordinatesSpecial,
                    secondTranslateAnimationX = secondTranslateCoordinatesSpecial,
                    typeButton = ButtonType.SPECIAL
                )
            }

        }

    }
    OnBackPressButton(onBackPress)


    LaunchedEffect(Unit) {
        isFirstTranslateEasy = !isFirstTranslateEasy
        delay(200)
        isFirstTranslateMedium = !isFirstTranslateMedium
        delay(200)
        isFirstTranslateHard = !isFirstTranslateHard
        delay(200)
        isFirstTranslateSpecial = !isFirstTranslateSpecial
        delay(200)

        onWaitClickButton()

        isSecondTranslateEasy = !isSecondTranslateEasy
        delay(200)
        isSecondTranslateMedium = !isSecondTranslateMedium
        delay(200)
        isSecondTranslateHard = !isSecondTranslateHard
        delay(200)
        isSecondTranslateSpecial = !isSecondTranslateSpecial
        delay(1050)

        when (buttonType) {
            ButtonType.INIT -> {}
            ButtonType.EASY -> {
                AppNavigationScreens.putScreenState(
                    AppNavigationScreens.Game(
                        context = context,
                        gameSettingsState = GameSettingsState.Easy,
                        cardList = getCardList(repository, GameSettingsState.Easy)
                    )
                )
            }

            ButtonType.MEDIUM -> {
                AppNavigationScreens.putScreenState(
                    AppNavigationScreens.Game(
                        context = context,
                        gameSettingsState = GameSettingsState.Medium,
                        cardList = getCardList(repository, GameSettingsState.Medium)
                    )
                )
            }

            ButtonType.HARD -> {
                AppNavigationScreens.putScreenState(
                    AppNavigationScreens.Game(
                        context = context,
                        gameSettingsState = GameSettingsState.Hard,
                        cardList = getCardList(repository, GameSettingsState.Hard)
                    )
                )
            }

            ButtonType.SPECIAL -> {
                AppNavigationScreens.putScreenState(
                    AppNavigationScreens.Game(
                        context = context,
                        gameSettingsState = GameSettingsState.Special,
                        cardList = getCardList(repository, GameSettingsState.Special)
                    )
                )
            }
        }
        buttonType = ButtonType.INIT

    }

    BackHandler {
        onBackPress()
    }
}


//region ColorsButton
@Composable
fun ColorsButton(
    text: String,
    firstTranslateAnimationX: Dp,
    secondTranslateAnimationX: Dp,
    typeButton: ButtonType
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .offset(x = firstTranslateAnimationX - secondTranslateAnimationX)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Magenta),
                    start = Offset(0.dp.toPx(), 0.dp.toPx()),
                    end = Offset(100.dp.toPx(), -100.dp.toPx()),
                    tileMode = TileMode.Mirror,
                ),
                alpha = 0.7f
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0F)
        ),

        onClick = {
            buttonType = typeButton
        }
    ) {
        Text(text = text, fontSize = 20.sp, fontWeight = W700)
    }
}
//endregion

//region onWaitClickButton
suspend fun onWaitClickButton() {
    while (true) {
        if (buttonType == ButtonType.INIT) {
            delay(100)
        } else {
            break
        }
    }
}
//endregion



