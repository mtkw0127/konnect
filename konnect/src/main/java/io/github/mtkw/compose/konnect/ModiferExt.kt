package io.github.mtkw.compose.konnect

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import io.github.mtkw.compose.konnect.internal.idSaver
import java.util.UUID


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
    val id = rememberSaveable(saver = idSaver) { KonnectState.Id(UUID.randomUUID().toString()) }

    this.onGloballyPositioned { coordinates ->
        state.points[id] = KonnectionInfo(
            size = coordinates.size,
            positionFromParent = coordinates.positionInParent(),
            anchor = anchor,
        )
    }
}