package io.github.mtkw.compose.konnect.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.mtkw0127.compose.konnect.KonnectInfo.RectAnchor
import io.github.mtkw0127.compose.konnect.KonnectStyle
import io.github.mtkw0127.compose.konnect.KonnectType
import io.github.mtkw0127.compose.konnect.drawKonnect
import io.github.mtkw0127.compose.konnect.konnect
import io.github.mtkw0127.compose.konnect.rememberKonnectState
import kotlin.math.roundToInt

@Composable
@Preview(showBackground = true)
fun LineElbowBothStartEndSide() {
    val state1 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state2 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state3 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state4 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White)
            .wrapContentSize()
            .drawKonnect(state1)
            .drawKonnect(state2)
            .drawKonnect(state3)
            .drawKonnect(state4)
    ) {
        Text(
            text = "Center",
            modifier = Modifier
                .konnect(
                    state = state1,
                    anchor = RectAnchor.CenterEnd
                )
                .konnect(
                    state = state2,
                    anchor = RectAnchor.CenterEnd,
                )
                .konnect(
                    state = state3,
                    anchor = RectAnchor.CenterStart,
                )
                .konnect(
                    state = state4,
                    anchor = RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item1",
            modifier = Modifier
                .offset(x = 100.dp, y = 50.dp)
                .konnect(
                    state = state1,
                    anchor = RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item2",
            modifier = Modifier
                .offset(x = 100.dp, y = (-50).dp)
                .konnect(
                    state = state2,
                    anchor = RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item3",
            modifier = Modifier
                .offset(x = (-100).dp, y = (-50).dp)
                .konnect(
                    state = state3,
                    anchor = RectAnchor.CenterEnd,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item4",
            modifier = Modifier
                .offset(x = (-100).dp, y = 50.dp)
                .konnect(
                    state = state4,
                    anchor = RectAnchor.CenterEnd,
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LineElbowSideAndVertical() {
    val state1 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state2 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state3 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state4 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White)
            .wrapContentSize()
            .drawKonnect(state1)
            .drawKonnect(state2)
            .drawKonnect(state3)
            .drawKonnect(state4)
    ) {
        Text(
            text = "Center",
            modifier = Modifier
                .konnect(
                    state = state1,
                    anchor = RectAnchor.BottomCenter
                )
                .konnect(
                    state = state2,
                    anchor = RectAnchor.TopCenter,
                )
                .konnect(
                    state = state3,
                    anchor = RectAnchor.TopCenter,
                )
                .konnect(
                    state = state4,
                    anchor = RectAnchor.BottomCenter,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item1",
            modifier = Modifier
                .offset(x = 100.dp, y = 50.dp)
                .konnect(
                    state = state1,
                    anchor = RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item2",
            modifier = Modifier
                .offset(x = 100.dp, y = (-50).dp)
                .konnect(
                    state = state2,
                    anchor = RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item3",
            modifier = Modifier
                .offset(x = (-100).dp, y = (-50).dp)
                .konnect(
                    state = state3,
                    anchor = RectAnchor.CenterEnd,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item4",
            modifier = Modifier
                .offset(x = (-100).dp, y = 50.dp)
                .konnect(
                    state = state4,
                    anchor = RectAnchor.CenterEnd,
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LineElbowBothTopBottomSide() {
    val state1 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state2 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state3 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    val state4 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White)
            .wrapContentSize()
            .drawKonnect(state1)
            .drawKonnect(state2)
            .drawKonnect(state3)
            .drawKonnect(state4)
    ) {
        Text(
            text = "Center",
            modifier = Modifier
                .konnect(
                    state = state1,
                    anchor = RectAnchor.BottomCenter
                )
                .konnect(
                    state = state2,
                    anchor = RectAnchor.TopCenter,
                )
                .konnect(
                    state = state3,
                    anchor = RectAnchor.TopCenter,
                )
                .konnect(
                    state = state4,
                    anchor = RectAnchor.BottomCenter,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item1",
            modifier = Modifier
                .offset(x = 100.dp, y = 50.dp)
                .konnect(
                    state = state1,
                    anchor = RectAnchor.TopCenter,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item2",
            modifier = Modifier
                .offset(x = 100.dp, y = (-50).dp)
                .konnect(
                    state = state2,
                    anchor = RectAnchor.BottomCenter,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item3",
            modifier = Modifier
                .offset(x = (-100).dp, y = (-50).dp)
                .konnect(
                    state = state3,
                    anchor = RectAnchor.BottomCenter,
                )
                .padding(10.dp)
        )

        Text(
            text = "Item4",
            modifier = Modifier
                .offset(x = (-100).dp, y = 50.dp)
                .konnect(
                    state = state4,
                    anchor = RectAnchor.TopCenter,
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LineElbowDrag() {
    var offsetPosition = remember { mutableStateOf(IntOffset.Zero) }
    val state = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetPosition.value = offsetPosition.value.copy(
                        x = offsetPosition.value.x + dragAmount.x.roundToInt(),
                        y = offsetPosition.value.y + dragAmount.y.roundToInt()
                    )
                }
            }
            .size(300.dp)
            .background(Color.White)
            .wrapContentSize()
            .drawKonnect(state)
    ) {
        Text(
            text = "Center",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = RectAnchor.BottomCenter
                )
                .padding(10.dp)
        )

        Text(
            text = "Item",
            modifier = Modifier
                .offset { offsetPosition.value }
                .konnect(
                    state = state,
                    anchor = RectAnchor.TopCenter,
                )
                .padding(10.dp)
        )
    }
}