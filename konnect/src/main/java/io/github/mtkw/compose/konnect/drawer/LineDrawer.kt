package io.github.mtkw.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw.compose.konnect.KonnectInfo
import io.github.mtkw.compose.konnect.KonnectStyle

object LineDrawer : KonnectDrawer {
    override fun ContentDrawScope.draw(
        start: KonnectInfo,
        end: KonnectInfo,
        style: KonnectStyle
    ) {
        drawLine(
            color = style.color,
            start = start.anchoredPosition(),
            end = end.anchoredPosition(),
            strokeWidth = style.strokeWidth.toPx()
        )
    }
}