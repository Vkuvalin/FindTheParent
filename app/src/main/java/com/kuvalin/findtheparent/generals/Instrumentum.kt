package com.kuvalin.findtheparent.generals

import android.app.Application
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.kuvalin.findtheparent.di.ApplicationComponent
import com.kuvalin.findtheparent.di.DaggerApplicationComponent


object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}


/*

1. Перевод в пиксели:
@Composable
fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}

2. Получение ширины экрана:

Вариант-1:
val configuration = LocalConfiguration.current
val screenHeight = configuration.screenHeightDp.dp.toPx()
val screenWidth = configuration.screenWidthDp.dp.toPx()



Вариант-2: (спорная хрень)

class Size {
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }
    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }
}


val size = Size()
val screenHeight = size.height()

Box(modifier = Modifier.height((screenHeigh/2).dp)) {
    //content
}


// 3. Отключает анимацию у кнопок
// https://stackoverflow.com/questions/66703448/how-to-disable-ripple-effect-when-clicking-in-jetpack-compose
object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}


*/





/* //
// https://stackoverflow.com/questions/66251718/scaling-button-animation-in-jetpack-compose
@Composable
fun AnimatedButton() {
    val boxHeight = animatedFloat(initVal = 50f)
    val relBoxWidth = animatedFloat(initVal = 1.0f)
    val fontSize = animatedFloat(initVal = 16f)

    fun animateDimensions() {
        boxHeight.animateTo(45f)
        relBoxWidth.animateTo(0.95f)
       // fontSize.animateTo(14f)
    }

    fun reverseAnimation() {
        boxHeight.animateTo(50f)
        relBoxWidth.animateTo(1.0f)
        //fontSize.animateTo(16f)
    }

        Box(
        modifier = Modifier
            .height(boxHeight.value.dp)
            .fillMaxWidth(fraction = relBoxWidth.value)

            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
            .clickable { }
            .pressIndicatorGestureFilter(
                onStart = {
                    animateDimensions()
                },
                onStop = {
                    reverseAnimation()
                },
                onCancel = {
                    reverseAnimation()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Explore Airbnb", fontSize = fontSize.value.sp, color = Color.White)
    }
}

*/



//region sortedSetOf
// Под капотом он ссылается на TreeSet, а в лямба выражении указывается, по каким парам сортировать.
// Это короче метод сортиворки массива (потом когда-нибудь почитать)
//    private val shopList = sortedSetOf<ShopItem>(object : Comparator<ShopItem>{
//        override fun compare(p0: ShopItem?, p1: ShopItem?): Int {
//        }
//    })
//private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
//endregion



//region Backup TopAppBar
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




//class FindTheParentsApplication: Application() {
//
//    val component by lazy {
//        DaggerApplicationComponent.factory().create(this)
//    }
//
//}
//
//
//@Composable
//fun getApplicationComponent(): ApplicationComponent {
//    return (LocalContext.current.applicationContext as FindTheParentsApplication).component
//}



// Прикольная функция для списков fold
/*

val items = listOf(1, 2, 3, 4, 5)

// Lambdas are code blocks enclosed in curly braces.
items.fold(0, {
    // When a lambda has parameters, they go first, followed by '->'
    acc: Int, i: Int ->
    print("acc = $acc, i = $i, ")
    val result = acc + i
    println("result = $result")
    // The last expression in a lambda is considered the return value:
    result
})

// Parameter types in a lambda are optional if they can be inferred:
val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

// Function references can also be used for higher-order function calls:
val product = items.fold(1, Int::times)

acc = 0, i = 1, result = 1
acc = 1, i = 2, result = 3
acc = 3, i = 3, result = 6
acc = 6, i = 4, result = 10
acc = 10, i = 5, result = 15
joinedToString = Elements: 1 2 3 4 5
product = 120


Имена для лямбды!
typealias ClickHandler = (Button, ClickEvent) -> Unit


Во как ещё можно!
Обозначение стрелки правоассоциативно, (Int) -> (Int) -> Unit эквивалентно предыдущему примеру,
но не ((Int) -> (Int)) -> Unit


АНОНИМНАЯ ФУНКЦИЯ!
fun(s: String): Int { return s.toIntOrNull() ?: 0 }

*/