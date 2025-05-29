package io.github.mtkw0127.compose.konnect

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize


data class KonnectInfo(
    private val size: IntSize,
    private val positionFromParent: Offset,
    val anchor: RectAnchor,
    val pointRole: PointRole,
) {
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

    enum class Position {
        Corner, StartEndSide, TopBottomSide, Center
    }

    enum class RectAnchor(
        val position: Position,
    ) {
        TopStart(
            position = Position.Corner,
        ),
        TopCenter(
            position = Position.TopBottomSide,
        ),
        TopEnd(
            position = Position.Corner,
        ),
        CenterStart(
            position = Position.StartEndSide,
        ),
        Center(
            position = Position.Corner
        ),
        CenterEnd(
            position = Position.StartEndSide,
        ),
        BottomStart(
            position = Position.Corner
        ),
        BottomCenter(
            position = Position.TopBottomSide,
        ),
        BottomEnd(
            position = Position.Corner
        ),
    }

    enum class PointRole {
        None,
        Start,
        End,
        Both,
    }
}