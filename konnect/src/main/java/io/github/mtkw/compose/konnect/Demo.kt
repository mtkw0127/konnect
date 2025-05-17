package io.github.mtkw.compose.konnect

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

fun Modifier.drawKonnect(
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

fun Modifier.konnect(
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
fun rememberKonnectState(
    style: KonnectStyle = KonnectStyle.Line.Default,
): KonnectState = remember {
    return@remember KonnectState(
        style = style,
    )
}