package com.kuvalin.findtheparent.generals

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.kuvalin.findtheparent.ui.theme.Orange


@Composable
fun OnBackPressButton(onBackPress: () -> Unit) {

    val firstPositionXOnScreen = 20.dp
    val firstPositionYOnScreen = 0.dp

    Canvas(
        modifier = Modifier
            .offset(firstPositionXOnScreen + 20.dp, firstPositionYOnScreen)
            .size(60.dp, 45.dp)
            .clickable(
                onClick = onBackPress,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        val firstPositionXOnCanvas = 0.dp.toPx()
        val firstPositionYOnCanvas = 0.dp.toPx()
        val firstArrowPositionXOnCanvas = 8.dp.toPx()
        val firstArrowPositionYOnCanvas = 20.dp.toPx()
        drawPath(
            path = Path().apply {
                moveTo(firstPositionXOnCanvas, firstPositionYOnCanvas)
                lineTo(firstPositionXOnCanvas, firstPositionYOnCanvas + 15.dp.toPx())
                lineTo(firstPositionXOnCanvas + 60.dp.toPx(), firstPositionYOnCanvas + 15.dp.toPx())
                lineTo(firstPositionXOnCanvas + 60.dp.toPx(), firstPositionYOnCanvas)
                lineTo(firstPositionXOnCanvas, firstPositionYOnCanvas)
            },
            brush = Brush.linearGradient(
                colors = listOf(Color.Magenta, Color.Cyan, Orange),
                start = Offset(0.dp.toPx(), 0.dp.toPx()),
                end = Offset(0.dp.toPx(), 150.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            style = Fill
        )

        drawCircle(
            center = Offset(firstPositionXOnCanvas + 30.dp.toPx(), firstPositionYOnCanvas + 15.dp.toPx()),
            brush = Brush.linearGradient(
                colors = listOf(Color.Magenta, Color.Cyan, Orange),
                start = Offset(0.dp.toPx(), 0.dp.toPx()),
                end = Offset(0.dp.toPx(), 150.dp.toPx()),
                tileMode = TileMode.Mirror
            ),
            radius = 30.dp.toPx(),
            style = Fill
        )

        drawPath(
            path = Path().apply {
                moveTo(firstArrowPositionXOnCanvas, firstArrowPositionYOnCanvas)
                lineTo(firstArrowPositionXOnCanvas + 25.dp.toPx(), firstArrowPositionYOnCanvas + 15.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas + 20.dp.toPx(), firstArrowPositionYOnCanvas + 5.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas + 40.dp.toPx(), firstArrowPositionYOnCanvas + 5.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas + 40.dp.toPx(), firstArrowPositionYOnCanvas - 5.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas + 20.dp.toPx(), firstArrowPositionYOnCanvas - 5.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas + 25.dp.toPx(), firstArrowPositionYOnCanvas - 15.dp.toPx())
                lineTo(firstArrowPositionXOnCanvas, firstArrowPositionYOnCanvas)
            },
            color = Color.White,
            style = Fill
        )
    }
}
