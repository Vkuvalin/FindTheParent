package com.kuvalin.findtheparent.presentation.mainmenu


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.generals.NoRippleTheme
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.CardStyleState
import com.kuvalin.findtheparent.presentation.welcome.toPx
import com.kuvalin.findtheparent.ui.theme.ParentBlue
import com.kuvalin.findtheparent.ui.theme.ParentRed


// На тему удаления тени при нажатии и тп
// https://stackoverflow.com/questions/69076711/how-to-set-gradient-background-in-topappbar-using-jetpack-compose

@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainMenu() {

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

    // TODO - попробовать использовать из Instrumentum
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp


    ModalNavigationDrawer(
        modifier = Modifier,
        drawerContent = {
            Column(
                modifier = Modifier
                    .width(screenWidth / 2)
                    .fillMaxHeight()
                    .background(color = Color.White)
            ) {
                DropDownMenuStyleCard()
            }
        },
    ) {
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textBrush = Brush.linearGradient(
                colors = listOf(Color.Cyan, Color.Magenta),
                start = Offset(0.dp.toPx(), rotateColorAnimation2.dp.toPx()),
                end = Offset(rotateColorAnimation2.dp.toPx(), -rotateColorAnimation.dp.toPx()),
                tileMode = TileMode.Mirror,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "Find",
                    fontWeight = W500,
                    style = TextStyle(brush = textBrush),
                    fontSize = 42.sp
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    modifier = Modifier,
                    text = "The",
                    fontWeight = W500,
                    style = TextStyle(brush = textBrush),
                    fontSize = 42.sp
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    modifier = Modifier,
                    text = "Parents",
                    fontWeight = W500,
                    style = TextStyle(brush = textBrush),
                    fontSize = 42.sp
                )
            }


            ParentsCardsBox(rotateColorAnimation, rotateColorAnimation2)
            Score()

            Spacer(modifier = Modifier.height(100.dp))
            Spacer(modifier = Modifier.height(100.dp))
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            alpha = 1f
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0F)
                    ),
                    onClick = {
                        AppNavigationScreens.putScreenState(AppNavigationScreens.GameSettingsMenu)
                    }
                ) {
                    Text(text = "НАЧАТЬ!")
                }
            }
        }
    }

}


//region DropDownMenuStyleCard
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuStyleCard() {

    val items = listOf(
        stringResource(R.string.cardStyle_1),
        stringResource(R.string.cardStyle_2),
        stringResource(R.string.cardStyle_3)
    )
    var expanded by remember { mutableStateOf(false) }
    // Как ещё можно обыграть? + Должно тянуть из базы. Можно в сам стейт установить же!
    var selected by rememberSaveable { mutableStateOf(
        when(CardStyleState.cardStyleState.value) {
            is CardStyleState.Style1 -> {
                items[0]
            }
            is CardStyleState.Style2 -> {
                items[1]
            }
            is CardStyleState.Style3 -> {
                items[2]
            }
        }
    ) }

    // ExposedDropdownMenu Colors
    val contentColor = Color(0xFF6E3CC5)


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selected,
            onValueChange = {},
            label = { Text("Стиль карточек") },
            trailingIcon = {
                ExposedDropdownMenuDefaults
                    .TrailingIcon(
                        expanded = expanded
                    )
            },
            modifier = Modifier.menuAnchor(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White,
                cursorColor = contentColor,
                focusedIndicatorColor = contentColor,
                unfocusedIndicatorColor = Color.Gray,
                focusedTrailingIconColor = contentColor,
                unfocusedTrailingIconColor = Color.Gray,
                focusedLabelColor = contentColor,
                unfocusedLabelColor = Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        when (item) {
                            items[0] -> {
                                CardStyleState.putCardStyleState(CardStyleState.Style1)
                            }

                            items[1] -> {
                                CardStyleState.putCardStyleState(CardStyleState.Style2)
                            }

                            items[2] -> {
                                CardStyleState.putCardStyleState(CardStyleState.Style3)
                            }
                        }
                        selected = item
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = contentColor,
                        leadingIconColor = contentColor,
                        trailingIconColor = contentColor,
                    )
                )
            }
        }
    }
}
//endregion

//region ParentsCardsBox
@Composable
fun ParentsCardsBox(animationVector: Float, animationVector2: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParentCard("МАМКА", R.drawable.mama, ParentRed, animationVector, animationVector2)
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParentCard("ПАПКА", R.drawable.papa, ParentBlue, animationVector, animationVector2)
        }
    }
}
//endregion

//region ParentCard
@Composable
fun ParentCard(
    name: String,
    id: Int,
    color: Color,
    animationVector: Float,
    animationVector2: Float
) {
    Text(
        modifier = Modifier
            .padding(bottom = 16.dp),
        text = name,
        fontWeight = W500,
        color = color,
        fontSize = 32.sp
    )
    Box(
        modifier = Modifier
            .size(150.dp)
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(8.dp)
        )
    }
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {}
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
}
//endregion

//region Score
@Composable
fun Score() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.GameScore),
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
//endregion