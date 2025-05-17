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
        if (positions.size > 2) {
            throw IllegalStateException("Konnect can only connect two points at a time.")
        }
        if (positions.size == 2) {
            val start = positions[0]
            val end = positions[1]
            with(state.style.drawer) {
                draw(
                    start = start,
                    end = end,
                    style = state.style
                )
            }
        }
    }
)

fun Modifier.konnect(
    state: KonnectState,
    anchor: KonnectInfo.RectAnchor = KonnectInfo.RectAnchor.Center,
): Modifier = composed {
    val id = rememberSaveable(saver = idSaver) { KonnectState.Id(UUID.randomUUID().toString()) }

    this.onGloballyPositioned { coordinates ->
        state.points[id] = KonnectInfo(
            size = coordinates.size,
            positionFromParent = coordinates.positionInParent(),
            anchor = anchor,
        )
    }
}