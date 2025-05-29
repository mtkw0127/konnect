package io.github.mtkw0127.compose.konnect.drawer

import androidx.compose.ui.geometry.Offset
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

                manhattanPath(
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

    private fun manhattanPath(
        start: KonnectInfo,
        end: KonnectInfo,
    ): List<Offset> {
        val startOffset = start.anchoredPosition()
        val endOffset = end.anchoredPosition()
        if (start.anchor.position == KonnectInfo.Position.StartEndSide && end.anchor.position == KonnectInfo.Position.StartEndSide) {
            return buildList {
                add(startOffset)
                if (startOffset.x != endOffset.x) {
                    add(
                        Offset(
                            (startOffset.x + endOffset.x) / 2,
                            startOffset.y
                        )
                    )
                }
                if (startOffset.y != endOffset.y) {
                    add(
                        Offset(
                            (startOffset.x + endOffset.x) / 2,
                            endOffset.y
                        )
                    )
                }
                add(endOffset)
            }
        } else if (start.anchor.position == KonnectInfo.Position.TopBottomSide && end.anchor.position == KonnectInfo.Position.TopBottomSide) {
            return buildList {
                add(startOffset)
                if (startOffset.y != endOffset.y) {
                    add(
                        Offset(
                            startOffset.x,
                            (startOffset.y + endOffset.y) / 2
                        )
                    )
                }
                if (startOffset.y != endOffset.y) {
                    add(
                        Offset(
                            endOffset.x,
                            (startOffset.y + endOffset.y) / 2
                        )
                    )
                }
                add(endOffset)
            }
        } else {
            return buildList {
                add(startOffset)
                if (start.anchor.position == KonnectInfo.Position.StartEndSide) {
                    add(
                        Offset(
                            endOffset.x,
                            startOffset.y
                        )
                    )
                }
                if (start.anchor.position == KonnectInfo.Position.TopBottomSide) {
                    add(
                        Offset(
                            startOffset.x,
                            endOffset.y
                        )
                    )
                }
                add(endOffset)
            }
        }
    }
}