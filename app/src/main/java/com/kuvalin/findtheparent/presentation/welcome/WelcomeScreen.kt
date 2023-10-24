package com.kuvalin.findtheparent.presentation.welcome


import android.graphics.Paint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuvalin.findtheparent.ui.theme.WhiteAppBackground
import com.kuvalin.findtheparent.ui.theme.Beige
import com.kuvalin.findtheparent.ui.theme.Blue
import com.kuvalin.findtheparent.ui.theme.Orange
import com.kuvalin.findtheparent.ui.theme.Red
import com.kuvalin.findtheparent.ui.theme.White
import com.kuvalin.findtheparent.ui.theme.Yellow
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(onStartMainMenuClick: () -> Unit) {

    var isBrainAnimationLaunched by remember { //Тут сразу после проигрывания анимаций
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AnimatedContent(
            targetState = isBrainAnimationLaunched,
            transitionSpec = {
                fadeIn(tween(durationMillis = 2000)) with fadeOut(tween(durationMillis = 2000))
            }, label = ""
        )
        { shouldLaunchFirstScreen ->
            if (shouldLaunchFirstScreen) {
                CompanyScreenAnimation()
            } else {
                BrainScreenAnimation(onStartMainMenuClick)
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(8500)
        isBrainAnimationLaunched = !isBrainAnimationLaunched
        // В идеале нужно было бы отсюда вернуть булевое значение, но я просто высчитаю время.
    }
}


@Composable
fun CompanyScreenAnimation() {

    //region Animation
    // Letter K
    var isFirstTranslateLetterK by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesLetterK by animateDpAsState(
        targetValue = if (isFirstTranslateLetterK) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )

    var isSecondTranslateLetterK by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesLetterK by animateDpAsState(
        targetValue = if (isSecondTranslateLetterK) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )


    // Letter S
    var isFirstTranslateLetterS by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesLetterS by animateDpAsState(
        targetValue = if (isFirstTranslateLetterS) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )

    var isSecondTranslateLetterS by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesLetterS by animateDpAsState(
        targetValue = if (isSecondTranslateLetterS) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )


    // Letter U
    var isFirstTranslateLetterU by remember { mutableStateOf(true) }
    val firstTranslateCoordinatesLetterU by animateDpAsState(
        targetValue = if (isFirstTranslateLetterU) 500.dp else 0.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )

    var isSecondTranslateLetterU by remember { mutableStateOf(true) }
    val secondTranslateCoordinatesLetterU by animateDpAsState(
        targetValue = if (isSecondTranslateLetterU) 0.dp else 500.dp,
        animationSpec = tween(
            delayMillis = 500,
            durationMillis = 4000
        ),
        label = ""
    )

    // GAMES
    var isIncreasedGames by remember { mutableStateOf(true) }
    val sizeAnimationGames by animateIntAsState(
        targetValue = if (isIncreasedGames) 0 else 32,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    var infiniteGames by remember { mutableStateOf(true) }
    val alphaAnimationGames by animateFloatAsState(
        targetValue = if (infiniteGames) 1f else 0f,
        animationSpec = tween(
            durationMillis = 4000
        ),
        label = ""
    )
    //endregion

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 50.dp)
                .weight(4f)
        )
        Box(
            modifier = Modifier.weight(1f),
        ) {
            LetterU(
                firstTranslateCoordinatesAnimation = firstTranslateCoordinatesLetterU,
                secondTranslateCoordinatesAnimation = secondTranslateCoordinatesLetterU
            )
            LetterS(
                firstTranslateCoordinatesAnimation = firstTranslateCoordinatesLetterS,
                secondTranslateCoordinatesAnimation = secondTranslateCoordinatesLetterS
            )
            LetterK(
                firstTranslateCoordinatesAnimation = firstTranslateCoordinatesLetterK,
                secondTranslateCoordinatesAnimation = secondTranslateCoordinatesLetterK
            )
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .alpha(alphaAnimationGames),
            text = "GAMES",
            fontSize = sizeAnimationGames.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Red
        )
        Spacer(
            modifier = Modifier
                .padding(top = 50.dp)
                .weight(3f)
        )
    }


    LaunchedEffect(Unit) {
        isFirstTranslateLetterU = !isFirstTranslateLetterU
        delay(500)
        isFirstTranslateLetterS = !isFirstTranslateLetterS
        delay(500)
        isFirstTranslateLetterK = !isFirstTranslateLetterK
        delay(2000)
        isIncreasedGames = !isIncreasedGames
        delay(2000)
        infiniteGames = !infiniteGames
        isSecondTranslateLetterU = !isSecondTranslateLetterU
        delay(500)
        isSecondTranslateLetterS = !isSecondTranslateLetterS
        delay(500)
        isSecondTranslateLetterK = !isSecondTranslateLetterK
        delay(500)
        // В идеале нужно было бы отсюда вернуть булевое значение, но я просто высчитаю время.
    }

}


@Composable
fun BrainScreenAnimation(onStartMainMenuClick: () -> Unit) {

    //region Brain animation
    var isIncreasedYellowPart by remember { mutableStateOf(true) }
    val sizeAnimationYellowPart by animateFloatAsState(
        targetValue = if (isIncreasedYellowPart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    var isIncreasedRedPart by remember { mutableStateOf(true) }
    val sizeAnimationRedPart by animateFloatAsState(
        targetValue = if (isIncreasedRedPart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    var isIncreasedBeigePart by remember { mutableStateOf(true) }
    val sizeAnimationBeigePart by animateFloatAsState(
        targetValue = if (isIncreasedBeigePart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    var isIncreasedBluePart by remember { mutableStateOf(true) }
    val sizeAnimationBluePart by animateFloatAsState(
        targetValue = if (isIncreasedBluePart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    var isIncreasedOrangePart by remember { mutableStateOf(true) }
    val sizeAnimationOrangePart by animateFloatAsState(
        targetValue = if (isIncreasedOrangePart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    var isIncreasedWhitePart by remember { mutableStateOf(true) }
    val sizeAnimationWhitePart by animateFloatAsState(
        targetValue = if (isIncreasedWhitePart) 0f else 1.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )
    //endregion

    //region Circle animation scale
    val infiniteSmallCircleScale = rememberInfiniteTransition(label = "")
    val sizeAnimationSmallCircle by infiniteSmallCircleScale.animateFloat(
        initialValue = 0f,
        targetValue = 1.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val infiniteMediumCircleScale = rememberInfiniteTransition(label = "")
    val sizeAnimationMediumCircle by infiniteMediumCircleScale.animateFloat(
        initialValue = 0f,
        targetValue = 1.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val infiniteBigCircleScale = rememberInfiniteTransition(label = "")
    val sizeAnimationBigCircle by infiniteBigCircleScale.animateFloat(
        initialValue = 0f,
        targetValue = 2.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    //endregion

    //region Circle animation alpha
    val infiniteSmallCircleAlpha = rememberInfiniteTransition(label = "")
    val alphaAnimationSmallCircle by infiniteSmallCircleAlpha.animateFloat(
        initialValue = 0.8f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val infiniteMediumCircleAlpha = rememberInfiniteTransition(label = "")
    val alphaAnimationMediumCircle by infiniteMediumCircleAlpha.animateFloat(
        initialValue = 0.7f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val infiniteBigCircleAlpha = rememberInfiniteTransition(label = "")
    val alphaAnimationBigCircle by infiniteBigCircleAlpha.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    //endregion


    var isBrainAnimationEnd by remember { mutableStateOf(false) }


    var infiniteTap by remember { mutableStateOf(false) }
    val alphaAnimationTap by animateIntAsState(
        targetValue = if (infiniteTap) 255 else 0,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = isBrainAnimationEnd,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (isBrainAnimationEnd) {
                    onStartMainMenuClick()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
        ) {
            val correctionInCalculationsBrain = 7
            val correctionInCalculationsCircle = 0

            BigCircle(
                animationScale = sizeAnimationBigCircle,
                alphaAnimationSmallCircle,
                correctionInCalculationsCircle
            )
            MediumCircle(
                animationScale = sizeAnimationMediumCircle,
                alphaAnimationMediumCircle,
                correctionInCalculationsCircle
            )
            SmallCircle(
                animationScale = sizeAnimationSmallCircle,
                alphaAnimationBigCircle,
                correctionInCalculationsCircle
            )
            YellowPart(sizeAnimationYellowPart, correctionInCalculationsBrain)
            RedPart(sizeAnimationRedPart, correctionInCalculationsBrain)
            BeigePart(sizeAnimationBeigePart, correctionInCalculationsBrain)
            BluePart(sizeAnimationBluePart, correctionInCalculationsBrain)
            OrangePart(sizeAnimationOrangePart, correctionInCalculationsBrain)
            WhitePart(sizeAnimationWhitePart, correctionInCalculationsBrain)
            TapToScreen(alphaAnimationTap)
        }
    }


    LaunchedEffect(Unit) {
        delay(2000)
        isIncreasedYellowPart = !isIncreasedYellowPart
        delay(500)
        isIncreasedRedPart = !isIncreasedRedPart
        delay(450)
        isIncreasedBeigePart = !isIncreasedBeigePart
        delay(300)
        isIncreasedBluePart = !isIncreasedBluePart
        delay(300)
        isIncreasedOrangePart = !isIncreasedOrangePart
        delay(300)
        isIncreasedWhitePart = !isIncreasedWhitePart
        delay(500)
        infiniteTap = !infiniteTap
        delay(1000)
        isBrainAnimationEnd = !isBrainAnimationEnd
    }

}


// --------------------- BRAIN
//region YellowPart
@Composable
fun YellowPart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {
                    moveTo(firstPositionX, firstPositionY)
                    lineTo(firstPositionX, firstPositionY - 25.dp.toPx())
                    lineTo(firstPositionX + 16.dp.toPx(), firstPositionY - 40.dp.toPx())
                    lineTo(firstPositionX + 24.dp.toPx(), firstPositionY - 60.dp.toPx())
                    lineTo(firstPositionX + 45.dp.toPx(), firstPositionY - 55.dp.toPx())
                    lineTo(firstPositionX + 55.dp.toPx(), firstPositionY - 48.dp.toPx())
                    lineTo(firstPositionX + 75.dp.toPx(), firstPositionY - 28.dp.toPx())
                    lineTo(firstPositionX + 75.dp.toPx(), firstPositionY - 22.dp.toPx())
                    lineTo(firstPositionX + 52.dp.toPx(), firstPositionY - 2.dp.toPx())
                    lineTo(firstPositionX + 27.dp.toPx(), firstPositionY - 17.dp.toPx())
                    lineTo(firstPositionX, firstPositionY)
                },
                color = Yellow,
                style = Fill,
            )
        }
    }
}

//endregion
//region RedPart
@Composable
fun RedPart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {
                    moveTo(firstPositionX, firstPositionY)
                    lineTo(firstPositionX, firstPositionY - 25.dp.toPx())
                    lineTo(firstPositionX + 16.dp.toPx(), firstPositionY - 40.dp.toPx())
                    lineTo(firstPositionX + 24.dp.toPx(), firstPositionY - 60.dp.toPx())
                    lineTo(firstPositionX - 24.dp.toPx(), firstPositionY - 57.dp.toPx())
                    lineTo(firstPositionX - 49.dp.toPx(), firstPositionY - 45.dp.toPx())
                    lineTo(firstPositionX - 73.dp.toPx(), firstPositionY - 20.dp.toPx())
                    lineTo(firstPositionX - 71.dp.toPx(), firstPositionY + 8.dp.toPx())
                    lineTo(firstPositionX - 47.dp.toPx(), firstPositionY + 24.dp.toPx())
                    lineTo(firstPositionX - 32.dp.toPx(), firstPositionY + 24.dp.toPx())
                    lineTo(firstPositionX - 25.dp.toPx(), firstPositionY + 20.dp.toPx())
                    lineTo(firstPositionX, firstPositionY)
                },
                color = Red,
                style = Fill,
            )
        }
    }
}

//endregion
//region BeigePart
@Composable
fun BeigePart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {
                    moveTo(firstPositionX, firstPositionY)
                    lineTo(firstPositionX - 25.dp.toPx(), firstPositionY + 20.dp.toPx())
                    lineTo(firstPositionX - 25.dp.toPx(), firstPositionY + 35.dp.toPx())
                    lineTo(firstPositionX, firstPositionY + 46.dp.toPx())
                    lineTo(firstPositionX + 58.dp.toPx(), firstPositionY + 24.dp.toPx())
                    lineTo(firstPositionX + 59.dp.toPx(), firstPositionY + 13.dp.toPx())
                    lineTo(firstPositionX + 52.dp.toPx(), firstPositionY - 2.dp.toPx())
                    lineTo(firstPositionX + 27.dp.toPx(), firstPositionY - 17.dp.toPx())
                    lineTo(firstPositionX, firstPositionY)
                },
                color = Beige,
                style = Fill,
            )
        }
    }
}

//endregion
//region BluePart
@Composable
fun BluePart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val secondPositionX = center.x + 58.dp.toPx() - correctionInCalculations.dp.toPx()
        val secondPositionY = center.y + 24.dp.toPx()
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {

                    moveTo(secondPositionX, secondPositionY)
                    lineTo(secondPositionX + 10.dp.toPx(), secondPositionY + 4.dp.toPx())
                    lineTo(secondPositionX + 23.dp.toPx(), secondPositionY + 5.dp.toPx())
                    lineTo(secondPositionX + 28.dp.toPx(), secondPositionY - 12.dp.toPx())
                    lineTo(secondPositionX + 23.dp.toPx(), secondPositionY - 26.dp.toPx())
                    lineTo(secondPositionX + 25.dp.toPx(), secondPositionY - 39.dp.toPx())
                    lineTo(secondPositionX + 17.dp.toPx(), secondPositionY - 46.dp.toPx())
                    lineTo(secondPositionX - 6.dp.toPx(), secondPositionY - 26.dp.toPx())
                    lineTo(secondPositionX + 1.dp.toPx(), secondPositionY - 11.dp.toPx())
                    lineTo(secondPositionX, secondPositionY)

                },
                color = Blue,
                style = Fill,
            )
        }
    }
}

//endregion
//region OrangePart
@Composable
fun OrangePart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val secondPositionX = center.x + 58.dp.toPx() - correctionInCalculations.dp.toPx()
        val secondPositionY = center.y + 24.dp.toPx()
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {

                    moveTo(secondPositionX, secondPositionY)
                    lineTo(secondPositionX + 10.dp.toPx(), secondPositionY + 4.dp.toPx())
                    lineTo(secondPositionX + 11.dp.toPx(), secondPositionY + 22.dp.toPx())
                    lineTo(secondPositionX - 14.dp.toPx(), secondPositionY + 37.dp.toPx())
                    lineTo(secondPositionX - 36.dp.toPx(), secondPositionY + 25.dp.toPx())
                    lineTo(secondPositionX - 37.dp.toPx(), secondPositionY + 14.dp.toPx())
                    lineTo(secondPositionX, secondPositionY)
                },
                color = Orange,
                style = Fill,
            )
        }
    }
}

//endregion
//region WhitePart
@Composable
fun WhitePart(animationScale: Float, correctionInCalculations: Int) {
    Canvas(
        modifier = Modifier
    ) {
        val thirdPositionX = center.x + 7.dp.toPx() - correctionInCalculations.dp.toPx()
        val thirdPositionY = center.y + 44.dp.toPx()
        scale(scale = animationScale) {
            drawPath(
                path = Path().apply {
                    moveTo(thirdPositionX, thirdPositionY)
                    lineTo(thirdPositionX + 15.dp.toPx(), thirdPositionY + 10.dp.toPx())
                    lineTo(thirdPositionX + 20.dp.toPx(), thirdPositionY + 25.dp.toPx())
                    lineTo(thirdPositionX + 29.dp.toPx(), thirdPositionY + 24.dp.toPx())
                    lineTo(thirdPositionX + 29.dp.toPx(), thirdPositionY + 12.dp.toPx())
                    lineTo(thirdPositionX + 15.dp.toPx(), thirdPositionY + 5.dp.toPx())
                    lineTo(thirdPositionX + 14.dp.toPx(), thirdPositionY - 7.dp.toPx())
                    lineTo(thirdPositionX, thirdPositionY)
                },
                color = White,
                style = Fill,
            )
        }
    }
}
//endregion


// --------------------- CIRCLES
//region SmallCircle
@Composable
fun SmallCircle(animationScale: Float, animationAlpha: Float, correctionInCalculations: Int = 0) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawCircle(
                center = Offset(firstPositionX, firstPositionY),
                color = Orange,
                alpha = animationAlpha,
                radius = 100.dp.toPx(),
                style = Fill
            )
        }
    }
}

//endregion
//region MediumCircle
@Composable
fun MediumCircle(animationScale: Float, animationAlpha: Float, correctionInCalculations: Int = 0) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawCircle(
                center = Offset(firstPositionX, firstPositionY),
                color = White,
                alpha = animationAlpha,
                radius = 100.dp.toPx(),
                style = Fill
            )
        }
    }
}

//endregion
//region BigCircle
@Composable
fun BigCircle(animationScale: Float, animationAlpha: Float, correctionInCalculations: Int = 0) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x - correctionInCalculations.dp.toPx()
        val firstPositionY = center.y
        scale(scale = animationScale) {
            drawCircle(
                center = Offset(firstPositionX, firstPositionY),
                color = Yellow,
                alpha = animationAlpha,
                radius = 100.dp.toPx(),
                style = Fill
            )
        }
    }
}
//endregion


// --------------------- Text
//region TapToScreen
@Composable
fun TapToScreen(alphaAnimation: Int) {

    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX = center.x
        val firstPositionY = center.y + 130.dp.toPx()

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 24.sp.toPx()
            color = Color(0xFF959DCE).toArgb()
            alpha = alphaAnimation
        }

        drawContext.canvas.nativeCanvas.drawText(
            "Tap   to   screen",
            firstPositionX,
            firstPositionY,
            paint
        )
    }
}
//endregion


// --------------------- KSU (Company name)
//region LetterK
@Composable
fun LetterK(firstTranslateCoordinatesAnimation: Dp, secondTranslateCoordinatesAnimation: Dp) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX =
            (center.x - 30.dp.toPx()) - firstTranslateCoordinatesAnimation.toPx() + secondTranslateCoordinatesAnimation.toPx()
        val firstPositionY = center.y
        scale(1.05f) {
            drawPath(
                path = Path().apply {
                    moveTo(firstPositionX - 127.5.dp.toPx(), firstPositionY + 75.dp.toPx())
                    lineTo(firstPositionX - 127.5.dp.toPx(), firstPositionY - 75.dp.toPx())
                    lineTo(firstPositionX - 100.dp.toPx(), firstPositionY - 45.dp.toPx())
                    lineTo(firstPositionX - 100.dp.toPx(), firstPositionY - 5.dp.toPx())
                    lineTo(firstPositionX - 52.dp.toPx(), firstPositionY - 55.dp.toPx())
                    lineTo(firstPositionX - 52.dp.toPx(), firstPositionY - 30.dp.toPx())
                    lineTo(firstPositionX - 100.dp.toPx(), firstPositionY + 15.dp.toPx())
                    lineTo(firstPositionX - 52.dp.toPx(), firstPositionY + 55.dp.toPx())
                    lineTo(firstPositionX - 52.dp.toPx(), firstPositionY + 75.dp.toPx())
                    lineTo(firstPositionX - 100.dp.toPx(), firstPositionY + 40.dp.toPx())
                    lineTo(firstPositionX - 100.dp.toPx(), firstPositionY + 55.dp.toPx())
                    lineTo(firstPositionX - 127.5.dp.toPx(), firstPositionY + 75.dp.toPx())
                },
                style = Fill,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Magenta),
                    start = Offset(200.dp.toPx(), 0.dp.toPx()),
                    end = Offset(200.dp.toPx(), 200.dp.toPx()),
//                    tileMode = TileMode.Mirror
                    tileMode = TileMode.Repeated
                )
            )
        }

    }
}

//endregion
//region LetterS
@Composable
fun LetterS(firstTranslateCoordinatesAnimation: Dp, secondTranslateCoordinatesAnimation: Dp) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX =
            center.x - 10.dp.toPx() - firstTranslateCoordinatesAnimation.toPx() + secondTranslateCoordinatesAnimation.toPx()
        val firstPositionY = center.y

        drawCircle(
            center = Offset(firstPositionX, firstPositionY - 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Color.Magenta, Color.Cyan, Orange),
                start = Offset(200.dp.toPx(), 120.dp.toPx()),
                end = Offset(120.dp.toPx(), 0.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionX, firstPositionY + 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Color.Magenta, Color.Cyan, Orange),
                start = Offset(200.dp.toPx(), 120.dp.toPx()),
                end = Offset(120.dp.toPx(), 0.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )


        drawCircle(
            center = Offset(firstPositionX, firstPositionY + 35.dp.toPx()),
            color = WhiteAppBackground,
            radius = 15.dp.toPx(),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionX, firstPositionY - 35.dp.toPx()),
            color = WhiteAppBackground,
            radius = 15.dp.toPx(),
            style = Fill
        )

        drawPath(
            path = Path().apply {
                moveTo(firstPositionX, firstPositionY - 35.dp.toPx())
                lineTo(firstPositionX + 60.dp.toPx(), firstPositionY + 25.dp.toPx())

                moveTo(firstPositionX + 11.dp.toPx(), firstPositionY - 45.dp.toPx())
                lineTo(firstPositionX + 50.dp.toPx(), firstPositionY - 5.dp.toPx())


                lineTo(firstPositionX + 50.dp.toPx(), firstPositionY + 35.dp.toPx())
                lineTo(firstPositionX - 11.dp.toPx(), firstPositionY - 24.dp.toPx())
                lineTo(firstPositionX + 10.dp.toPx(), firstPositionY - 45.dp.toPx())
            },
            color = WhiteAppBackground,
            style = Fill,
        )

        drawPath(
            path = Path().apply {

                moveTo(firstPositionX - 11.dp.toPx(), firstPositionY + 45.dp.toPx())
                lineTo(firstPositionX - 50.dp.toPx(), firstPositionY + 5.dp.toPx())

                lineTo(firstPositionX - 50.dp.toPx(), firstPositionY - 35.dp.toPx())
                lineTo(firstPositionX + 11.dp.toPx(), firstPositionY + 24.dp.toPx())
                lineTo(firstPositionX - 10.dp.toPx(), firstPositionY + 45.dp.toPx())
            },
            color = WhiteAppBackground,
            style = Fill,
        )
    }
}

//endregion
//region LetterU
@Composable
fun LetterU(firstTranslateCoordinatesAnimation: Dp, secondTranslateCoordinatesAnimation: Dp) {
    Canvas(
        modifier = Modifier
    ) {
        val firstPositionX =
            center.x - 10.dp.toPx() - firstTranslateCoordinatesAnimation.toPx() + secondTranslateCoordinatesAnimation.toPx()
        val firstPositionY = center.y


        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY + 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Yellow, Color.Magenta, Color.Cyan),
                start = Offset(200.dp.toPx(), 120.dp.toPx()),
                end = Offset(120.dp.toPx(), 0.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )


        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY + 5.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Orange, Yellow, Color.Magenta, Color.Cyan),
                start = Offset(0.dp.toPx(), 200.dp.toPx()),
                end = Offset(200.dp.toPx(), 200.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )


        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY - 15.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Orange, Color.Magenta, Color.Cyan),
                start = Offset(0.dp.toPx(), 0.dp.toPx()),
                end = Offset(100.dp.toPx(), 100.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY - 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Color.Magenta, Color.Cyan),
                end = Offset(0.dp.toPx(), 200.dp.toPx()),
                start = Offset(100.dp.toPx(), 100.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 50.dp.toPx(),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY - 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Orange, Color.Magenta, Color.Cyan),
                start = Offset(200.dp.toPx(), 0.dp.toPx()),
                end = Offset(200.dp.toPx(), 200.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 40.dp.toPx(),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY - 25.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Yellow, Color.Magenta, Color.Cyan),
                end = Offset(200.dp.toPx(), 0.dp.toPx()),
                start = Offset(0.dp.toPx(), 0.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 30.dp.toPx(),
            style = Fill
        )


        drawCircle(
            center = Offset(firstPositionX + 127.5.dp.toPx(), firstPositionY + 25.dp.toPx()),
            color = WhiteAppBackground,
            radius = 20.dp.toPx(),
            style = Fill
        )

        drawPath(
            path = Path().apply {
                moveTo(firstPositionX + 127.5.dp.toPx(), firstPositionY + 25.dp.toPx())
                lineTo(firstPositionX + 127.5.dp.toPx(), firstPositionY - 75.dp.toPx())
            },
            color = WhiteAppBackground,
            style = Stroke(width = 40.dp.toPx()),
        )
    }
}
//endregion


@Composable
fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}