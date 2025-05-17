package io.github.mtkw.compose.konnect

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize

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