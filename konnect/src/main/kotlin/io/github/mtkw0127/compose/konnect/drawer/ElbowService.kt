package io.github.mtkw0127.compose.konnect.drawer

import androidx.compose.ui.geometry.Offset
import io.github.mtkw0127.compose.konnect.KonnectInfo

object ElbowService {
    fun manhattanPath(
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