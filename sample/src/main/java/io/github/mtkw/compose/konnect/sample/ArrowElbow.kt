package io.github.mtkw.compose.konnect.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import io.github.mtkw0127.compose.konnect.KonnectInfo.PointRole
import io.github.mtkw0127.compose.konnect.KonnectInfo.RectAnchor
import io.github.mtkw0127.compose.konnect.KonnectStyle
import io.github.mtkw0127.compose.konnect.KonnectType
import io.github.mtkw0127.compose.konnect.drawKonnect
import io.github.mtkw0127.compose.konnect.konnect
import io.github.mtkw0127.compose.konnect.rememberKonnectState
import kotlin.math.roundToInt

@Composable
@Preview(showBackground = true)
fun ArrowElbowSigne() {
    val state = rememberKonnectState(
        style = KonnectStyle.Arrow(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Row(
        modifier = Modifier
            .height(50.dp)
            .drawKonnect(state)
    ) {
        Text(
            text = "Item 1-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterEnd,
                    pointRole = PointRole.Start,
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))

        Text(
            text = "Item 1-2",
            modifier = Modifier
                .offset(y = 20.dp)
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterStart,
                    pointRole = PointRole.End
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ArrowElbowBoth() {
    val state = rememberKonnectState(
        style = KonnectStyle.Arrow(
            color = Color.Red,
            strokeWidth = 2.dp,
            type = KonnectType.ELBOW,
        )
    )
    Row(
        modifier = Modifier
            .height(50.dp)
            .drawKonnect(state)
    ) {
        Text(
            text = "Item 1-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterEnd,
                    pointRole = PointRole.Both,
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))

        Text(
            text = "Item 1-2",
            modifier = Modifier
                .offset(y = 20.dp)
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterStart,
                    pointRole = PointRole.Both
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ArrowElbowDrag() {
    var offsetPosition = remember { mutableStateOf(IntOffset.Zero) }
    val state = rememberKonnectState(
        style = KonnectStyle.Arrow(
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
                    anchor = RectAnchor.BottomCenter,
                    pointRole = PointRole.Both
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
                    pointRole = PointRole.Both
                )
                .padding(10.dp)
        )
    }
}