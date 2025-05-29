package io.github.mtkw0127.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw0127.compose.konnect.KonnectInfo
import io.github.mtkw0127.compose.konnect.KonnectStyle
import io.github.mtkw0127.compose.konnect.KonnectType

object LineDrawer : KonnectDrawer {
    override fun ContentDrawScope.draw(
        p1: KonnectInfo,
        p2: KonnectInfo,
        style: KonnectStyle
    ) {
        when (style.type) {
            KonnectType.STRAIGHT -> {
                drawLine(
                    color = style.color,
                    start = p1.anchoredPosition(),
                    end = p2.anchoredPosition(),
                    strokeWidth = style.strokeWidth.toPx()
                )
            }

            KonnectType.ELBOW -> {
                if (p1.anchor.position == KonnectInfo.Position.Corner || p2.anchor.position == KonnectInfo.Position.Corner) {
                    throw IllegalStateException("Elbow drawer requires both points to be border center anchors.")
                }

                ElbowService.manhattanPath(
                    start = p1,
                    end = p2,
                ).windowed(2) { offsets ->
                    drawLine(
                        color = style.color,
                        start = offsets[0],
                        end = offsets[1],
                        strokeWidth = style.strokeWidth.toPx()
                    )
                }
            }
        }
    }
}