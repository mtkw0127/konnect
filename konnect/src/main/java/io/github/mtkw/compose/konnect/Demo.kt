package io.github.mtkw.compose.konnect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.util.UUID

data class KonnectionInfo(
    val size: IntSize,
    private val positionFromParent: Offset,
    val anchor: RectAnchor,
) {
    enum class RectAnchor {
        TopStart,
        TopCenter,
        TopEnd,
        CenterStart,
        Center,
        CenterEnd,
        BottomStart,
        BottomCenter,
        BottomEnd
    }

    fun anchoredPosition(): Offset {
        return positionFromParent + when (anchor) {
            RectAnchor.TopStart -> Offset(0f, 0f)
            RectAnchor.TopCenter -> Offset(size.width / 2f, 0f)
            RectAnchor.TopEnd -> Offset(size.width.toFloat(), 0f)
            RectAnchor.CenterStart -> Offset(0f, size.height / 2f)
            RectAnchor.Center -> Offset(size.width / 2f, size.height / 2f)
            RectAnchor.CenterEnd -> Offset(size.width.toFloat(), size.height / 2f)
            RectAnchor.BottomStart -> Offset(0f, size.height.toFloat())
            RectAnchor.BottomCenter -> Offset(size.width / 2f, size.height.toFloat())
            RectAnchor.BottomEnd -> Offset(size.width.toFloat(), size.height.toFloat())
        }
    }
}

sealed interface KonnectStyle {
    val color: Color
    val strokeWidth: Dp

    data class Line(
        override val color: Color,
        override val strokeWidth: Dp,
    ) : KonnectStyle {
        companion object {
            val Default = Line(
                color = Color.Black,
                strokeWidth = 1.dp,
            )
        }
    }
}

data class KonnectState(
    val style: KonnectStyle,
    val points: MutableMap<Id, KonnectionInfo> = mutableMapOf(),
) {
    data class Id(val value: String)
}

@Composable
fun Sample() {
    val state1 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
        )
    )
    val state2 = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Blue,
            strokeWidth = 1.dp,
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(modifier = Modifier.drawKonnect(state1)) {
            Text(
                text = "Item 1-1",
                modifier = Modifier
                    .konnect(
                        state = state1,
                        anchor = KonnectionInfo.RectAnchor.CenterEnd
                    )
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = "Item 1-2",
                modifier = Modifier
                    .konnect(
                        state = state1,
                        anchor = KonnectionInfo.RectAnchor.CenterStart,
                    )
                    .padding(10.dp)
            )
        }

        Column(modifier = Modifier.drawKonnect(state2)) {
            Text(
                text = "Item 2-1",
                modifier = Modifier
                    .konnect(
                        state = state2,
                        anchor = KonnectionInfo.RectAnchor.BottomCenter
                    )
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Item 2-2",
                modifier = Modifier
                    .konnect(
                        state = state2,
                        anchor = KonnectionInfo.RectAnchor.TopCenter,
                    )
                    .padding(10.dp)
            )
        }
    }
}

private fun Modifier.drawKonnect(
    state: KonnectState,
): Modifier = this.then(
    Modifier.drawWithContent {
        drawContent()
        val positions = state.points.values.toList()
        if (positions.size >= 2) {
            for (i in 0 until positions.lastIndex) {
                val start = positions[i]
                val end = positions[i + 1]
                drawLine(
                    color = state.style.color,
                    start = start.anchoredPosition(),
                    end = end.anchoredPosition(),
                    strokeWidth = state.style.strokeWidth.toPx()
                )
            }
        }
    }
)

private fun Modifier.konnect(
    state: KonnectState,
    anchor: KonnectionInfo.RectAnchor = KonnectionInfo.RectAnchor.Center,
): Modifier = composed {
    val idSaver = Saver<KonnectState.Id, String>(
        save = { it.value },
        restore = { KonnectState.Id(it) }
    )
    val id = rememberSaveable(saver = idSaver) { KonnectState.Id(UUID.randomUUID().toString()) }

    this.onGloballyPositioned { coordinates ->
        state.points[id] = KonnectionInfo(
            size = coordinates.size,
            positionFromParent = coordinates.positionInParent(),
            anchor = anchor,
        )
    }
}

@Composable
private fun rememberKonnectState(
    style: KonnectStyle = KonnectStyle.Line.Default,
): KonnectState = remember {
    return@remember KonnectState(
        style = style,
    )
}

@Composable
@Preview
private fun SamplePreview() {
    Sample()
}