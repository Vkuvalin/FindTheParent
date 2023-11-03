package com.kuvalin.findtheparent.presentation.mainmenu


import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.kuvalin.findtheparent.R
import com.kuvalin.findtheparent.data.repository.CardListRepositoryImpl
import com.kuvalin.findtheparent.domain.entity.Card.Companion.UNDEFINED_ID
import com.kuvalin.findtheparent.domain.usecase.AddFatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.AddMatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.GetCardStyleStateUseCase
import com.kuvalin.findtheparent.domain.usecase.GetFatherPhotoCardUseCase
import com.kuvalin.findtheparent.domain.usecase.GetGameScoreUseCase
import com.kuvalin.findtheparent.domain.usecase.GetMatherPhotoCardUseCase
import com.kuvalin.findtheparent.generals.AppInitLoadState
import com.kuvalin.findtheparent.generals.CardStyleState
import com.kuvalin.findtheparent.generals.CardType
import com.kuvalin.findtheparent.generals.NoRippleTheme
import com.kuvalin.findtheparent.navigation.AppNavigationScreens
import com.kuvalin.findtheparent.presentation.welcome.toPx
import com.kuvalin.findtheparent.ui.theme.ParentBlue
import com.kuvalin.findtheparent.ui.theme.ParentRed
import com.kuvalin.findtheparent.ui.theme.checkedBorderColor
import com.kuvalin.findtheparent.ui.theme.checkedIconColor
import com.kuvalin.findtheparent.ui.theme.checkedThumbColor
import com.kuvalin.findtheparent.ui.theme.checkedTrackColor
import com.kuvalin.findtheparent.ui.theme.disabledCheckedBorderColor
import com.kuvalin.findtheparent.ui.theme.disabledCheckedIconColor
import com.kuvalin.findtheparent.ui.theme.disabledCheckedThumbColor
import com.kuvalin.findtheparent.ui.theme.disabledCheckedTrackColor
import com.kuvalin.findtheparent.ui.theme.disabledUncheckedBorderColor
import com.kuvalin.findtheparent.ui.theme.disabledUncheckedIconColor
import com.kuvalin.findtheparent.ui.theme.disabledUncheckedThumbColor
import com.kuvalin.findtheparent.ui.theme.disabledUncheckedTrackColor
import com.kuvalin.findtheparent.ui.theme.uncheckedBorderColor
import com.kuvalin.findtheparent.ui.theme.uncheckedIconColor
import com.kuvalin.findtheparent.ui.theme.uncheckedThumbColor
import com.kuvalin.findtheparent.ui.theme.uncheckedTrackColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// На тему удаления тени при нажатии и тп
// https://stackoverflow.com/questions/69076711/how-to-set-gradient-background-in-topappbar-using-jetpack-compose


var scoreMama = 0
var scorePapa = 0

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    repository: CardListRepositoryImpl,
    appInitLoadState: AppInitLoadState
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

    // TODO - попробовать использовать из Instrumentum
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var checkedSound by remember { mutableStateOf(false) }
    var checkedBackground by remember { mutableStateOf(false) }
    var checkedMiniature by remember { mutableStateOf(false) }
    var checkedScore by remember { mutableStateOf(false) }



    ModalNavigationDrawer(
        modifier = Modifier,
        drawerContent = {
            Column(
                modifier = Modifier
                    .width(screenWidth / 2)
                    .fillMaxHeight()
                    .background(color = Color.White)
            ) {
                DropDownMenuStyleCard(repository, appInitLoadState)
                //region Switch
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Звук", fontSize=16.sp)
                    Switch(
                        enabled = true,
                        checked = checkedSound,
                        colors = SwitchDefaults.colors(
                            disabledUncheckedBorderColor = disabledUncheckedBorderColor,
                            disabledUncheckedThumbColor = disabledUncheckedThumbColor,
                            disabledUncheckedTrackColor = disabledUncheckedTrackColor,
                            disabledUncheckedIconColor = disabledUncheckedIconColor,
                            disabledCheckedBorderColor = disabledCheckedBorderColor,
                            disabledCheckedTrackColor = disabledCheckedTrackColor,
                            disabledCheckedThumbColor = disabledCheckedThumbColor,
                            disabledCheckedIconColor = disabledCheckedIconColor,
                            uncheckedBorderColor = uncheckedBorderColor,
                            uncheckedThumbColor = uncheckedThumbColor,
                            uncheckedTrackColor = uncheckedTrackColor,
                            uncheckedIconColor = uncheckedIconColor,
                            checkedBorderColor = checkedBorderColor,
                            checkedTrackColor = checkedTrackColor,
                            checkedThumbColor = checkedThumbColor,
                            checkedIconColor = checkedIconColor
                        ),
                        onCheckedChange = {mChecked ->
                            checkedSound = mChecked
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Заставка", fontSize=16.sp)
                    Switch(
                        enabled = true,
                        checked = checkedMiniature,
                        colors = SwitchDefaults.colors(
                            disabledUncheckedBorderColor = disabledUncheckedBorderColor,
                            disabledUncheckedThumbColor = disabledUncheckedThumbColor,
                            disabledUncheckedTrackColor = disabledUncheckedTrackColor,
                            disabledUncheckedIconColor = disabledUncheckedIconColor,
                            disabledCheckedBorderColor = disabledCheckedBorderColor,
                            disabledCheckedTrackColor = disabledCheckedTrackColor,
                            disabledCheckedThumbColor = disabledCheckedThumbColor,
                            disabledCheckedIconColor = disabledCheckedIconColor,
                            uncheckedBorderColor = uncheckedBorderColor,
                            uncheckedThumbColor = uncheckedThumbColor,
                            uncheckedTrackColor = uncheckedTrackColor,
                            uncheckedIconColor = uncheckedIconColor,
                            checkedBorderColor = checkedBorderColor,
                            checkedTrackColor = checkedTrackColor,
                            checkedThumbColor = checkedThumbColor,
                            checkedIconColor = checkedIconColor
                        ),
                        onCheckedChange = {mChecked ->
                            checkedMiniature = mChecked
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Фон", fontSize=16.sp)
                    Switch(
                        enabled = true,
                        checked = checkedBackground,
                        colors = SwitchDefaults.colors(
                            disabledUncheckedBorderColor = disabledUncheckedBorderColor,
                            disabledUncheckedThumbColor = disabledUncheckedThumbColor,
                            disabledUncheckedTrackColor = disabledUncheckedTrackColor,
                            disabledUncheckedIconColor = disabledUncheckedIconColor,
                            disabledCheckedBorderColor = disabledCheckedBorderColor,
                            disabledCheckedTrackColor = disabledCheckedTrackColor,
                            disabledCheckedThumbColor = disabledCheckedThumbColor,
                            disabledCheckedIconColor = disabledCheckedIconColor,
                            uncheckedBorderColor = uncheckedBorderColor,
                            uncheckedThumbColor = uncheckedThumbColor,
                            uncheckedTrackColor = uncheckedTrackColor,
                            uncheckedIconColor = uncheckedIconColor,
                            checkedBorderColor = checkedBorderColor,
                            checkedTrackColor = checkedTrackColor,
                            checkedThumbColor = checkedThumbColor,
                            checkedIconColor = checkedIconColor
                        ),
                        onCheckedChange = {mChecked ->
                            checkedBackground = mChecked
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Обнулить счёт", fontSize=16.sp)
                    Switch(
                        enabled = true,
                        checked = checkedScore,
                        colors = SwitchDefaults.colors(
                            disabledUncheckedBorderColor = disabledUncheckedBorderColor,
                            disabledUncheckedThumbColor = disabledUncheckedThumbColor,
                            disabledUncheckedTrackColor = disabledUncheckedTrackColor,
                            disabledUncheckedIconColor = disabledUncheckedIconColor,
                            disabledCheckedBorderColor = disabledCheckedBorderColor,
                            disabledCheckedTrackColor = disabledCheckedTrackColor,
                            disabledCheckedThumbColor = disabledCheckedThumbColor,
                            disabledCheckedIconColor = disabledCheckedIconColor,
                            uncheckedBorderColor = uncheckedBorderColor,
                            uncheckedThumbColor = uncheckedThumbColor,
                            uncheckedTrackColor = uncheckedTrackColor,
                            uncheckedIconColor = uncheckedIconColor,
                            checkedBorderColor = checkedBorderColor,
                            checkedTrackColor = checkedTrackColor,
                            checkedThumbColor = checkedThumbColor,
                            checkedIconColor = checkedIconColor
                        ),
                        onCheckedChange = {mChecked ->
                            checkedScore = mChecked
                        }
                    )
                }
                //endregion
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


            ParentsCardsBox(repository, appInitLoadState)
            Score(repository, appInitLoadState)

            Spacer(modifier = Modifier.height(100.dp)) // TODO плохое решение! Переделать хотя бы на 1f
            Spacer(modifier = Modifier.height(100.dp))
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                //region Button "НАЧАТЬ"
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
                //endregion
            }
        }
    }
}


//region DropDownMenuStyleCard
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuStyleCard(repository: CardListRepositoryImpl, appInitLoadState: AppInitLoadState) {

    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.IO)
    var loadStyleComplete by remember { mutableStateOf(false) }
    var initLoad by remember { mutableStateOf(false) }

    if (!loadStyleComplete) {
        scope.launch {

            when (appInitLoadState) {
                is AppInitLoadState.Initial -> {
                    delay(500)
                }

                is AppInitLoadState.Successive -> {
                    delay(300)
                }
            }

            try {
                CardStyleState.putCardStyleState(
                    GetCardStyleStateUseCase(repository).invoke(),
                    context
                )
                loadStyleComplete = true
            } catch (e: Exception) {
                initLoad = true
            }
        }
    }

    if (loadStyleComplete || initLoad) {

        val items = listOf(
            stringResource(R.string.cardStyle_1),
            stringResource(R.string.cardStyle_2),
            stringResource(R.string.cardStyle_3)
        )
        var expanded by remember { mutableStateOf(false) }
        // Как ещё можно обыграть? + Должно тянуть из базы. Можно в сам стейт установить же!
        var selected by rememberSaveable {
            mutableStateOf(
                when (CardStyleState.cardStyleState.value) {
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
            )
        }

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
                label = { Text("Стиль карточек", fontSize=12.sp) },
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
                            scope.launch {
                                when (item) {
                                    items[0] -> {
                                        CardStyleState.putCardStyleState(
                                            CardStyleState.Style1,
                                            context
                                        )
                                    }

                                    items[1] -> {
                                        CardStyleState.putCardStyleState(
                                            CardStyleState.Style2,
                                            context
                                        )
                                    }

                                    items[2] -> {
                                        CardStyleState.putCardStyleState(
                                            CardStyleState.Style3,
                                            context
                                        )
                                    }
                                }
                                selected = item
                                expanded = false
                            }
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
}
//endregion

//region ParentsCardsBox
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ParentsCardsBox(repository: CardListRepositoryImpl, appInitLoadState: AppInitLoadState) {
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
            ParentCard("МАМКА", ParentRed, repository, appInitLoadState)
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ParentCard("ПАПКА", ParentBlue, repository, appInitLoadState)
        }
    }
}
//endregion

//region ParentCard
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ParentCard(
    name: String,
    color: Color,
    repository: CardListRepositoryImpl,
    appInitLoadState: AppInitLoadState
) {
    Text(
        modifier = Modifier
            .padding(bottom = 16.dp),
        text = name,
        fontWeight = W500,
        color = color,
        fontSize = 32.sp
    )
    GalleryImageSelector(name, repository, appInitLoadState)
}
//endregion

//region PickImageFromGallery
@SuppressLint("CoroutineCreationDuringComposition", "Recycle")
@Composable
fun GalleryImageSelector(
    name: String,
    repository: CardListRepositoryImpl,
    appInitLoadState: AppInitLoadState
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.IO)
    var initLoad by remember { mutableStateOf(false) }

    if (!initLoad) {
        scope.launch {
            when (appInitLoadState) {
                is AppInitLoadState.Initial -> {
                    delay(800)
                }

                is AppInitLoadState.Successive -> {
                    delay(300)
                }
            }
            selectedImageUri = when (name) {
                "МАМКА" -> {
                    GetMatherPhotoCardUseCase(repository).invoke(CardType.MATHER)?.imageUri
                }

                "ПАПКА" -> {
                    GetFatherPhotoCardUseCase(repository).invoke(CardType.FATHER)?.imageUri
                }

                else -> {
                    null
                }
            }
            initLoad = true
        }
    }

    if (initLoad) {

        // Это вариант с обычным получением фотки, без сохранения её во внутреннюю память
        // https://translated.turbopages.org/proxy_u/en-ru.ru.46d9e2c2-65420c09-fa180ffd-74722d776562/https/stackoverflow.com/questions/72025124/save-and-show-picked-picture-by-uri-after-app-restart
//        val getContent = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.GetContent()
//        ) { uri: Uri? ->
//            selectedImageUri = uri
//        }

        val getContent = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { newUri: Uri? ->
            if (newUri == null) return@rememberLauncherForActivityResult

            val input = context.contentResolver.openInputStream(newUri)
                ?: return@rememberLauncherForActivityResult
            val outputFile = context.filesDir.resolve("profilePic${(1..1_000_000).random()}.jpg")
            input.copyTo(outputFile.outputStream())
            selectedImageUri = outputFile.toUri()
        }




        Box(
            modifier = Modifier
                .size(150.dp)
        ) {
            Image(
                painter = if (selectedImageUri != null)
                    rememberAsyncImagePainter(model = selectedImageUri)
                else painterResource(if (name == "МАМКА") R.drawable.mama else R.drawable.papa),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            selectedImageUri?.let { uri ->
                scope.launch {
                    when (name) {
                        "МАМКА" -> {
                            AddMatherPhotoCardUseCase(repository).invoke(UNDEFINED_ID, uri)
                        }

                        "ПАПКА" -> {
                            AddFatherPhotoCardUseCase(repository).invoke(UNDEFINED_ID, uri)
                        }

                        else -> {}
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Magenta),
                        start = Offset(0.dp.toPx(), 0.dp.toPx()),
                        end = Offset(0.dp.toPx(), 0.dp.toPx()),
                        tileMode = TileMode.Mirror,
                    ),
                    alpha = 1f
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0F)
            ),
            onClick = {
                getContent.launch("image/*")
            }
        ) {
            Text(text = "добавить фото")
        }
    }
}
//endregion

//region Score
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Score(repository: CardListRepositoryImpl, appInitLoadState: AppInitLoadState) {

    val scope = CoroutineScope(Dispatchers.IO)
    var loadCompleted by remember { mutableStateOf(false) }

    if (!loadCompleted) {
        scope.launch {

            when (appInitLoadState) {
                is AppInitLoadState.Initial -> {
                    delay(800)
                }

                is AppInitLoadState.Successive -> {
                    delay(300)
                }
            }

            scoreMama = GetGameScoreUseCase(repository).invoke().mama
            scorePapa = GetGameScoreUseCase(repository).invoke().papa
            loadCompleted = true
        }
    }

    if (loadCompleted) {
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
                    text = "$scoreMama",
                    fontSize = 32.sp,
                    fontWeight = W500,
                    color = ParentRed
                )
                Spacer(modifier = Modifier.weight(3f))
                Text(
                    text = "$scorePapa",
                    fontSize = 32.sp,
                    fontWeight = W500,
                    color = ParentBlue
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

}
//endregion
