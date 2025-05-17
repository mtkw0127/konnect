package io.github.mtkw0127.compose.konnect

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import io.github.mtkw0127.compose.konnect.KonnectInfo.PointRole
import io.github.mtkw0127.compose.konnect.KonnectInfo.RectAnchor
import io.github.mtkw0127.compose.konnect.internal.idSaver
import java.util.UUID

fun Modifier.drawKonnect(
    state: KonnectState,
): Modifier = this.then(
    Modifier.drawWithContent {
        drawContent()
        val positions = state.points.values.toList()
        if (positions.size < 2) {
            throw IllegalStateException("Konnect requires two points to draw.")
        }
        if (positions.size > 2) {
            throw IllegalStateException("Konnect can only connect two points at a time.")
        }

        with(state.style.drawer) {
            draw(
                p1 = positions[0],
                p2 = positions[1],
                style = state.style
            )
        }
    }
)

fun Modifier.konnect(
    state: KonnectState,
    anchor: RectAnchor = RectAnchor.Center,
    pointRole: PointRole = PointRole.None,
): Modifier = composed {
    val id = rememberSaveable(saver = idSaver) { KonnectState.Id(UUID.randomUUID().toString()) }

    this.onGloballyPositioned { coordinates ->
        state.points[id] = KonnectInfo(
            size = coordinates.size,
            positionFromParent = coordinates.positionInParent(),
            anchor = anchor,
            pointRole = pointRole,
        )
    }
}