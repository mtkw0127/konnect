package io.github.mtkw0127.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw0127.compose.konnect.KonnectInfo
import io.github.mtkw0127.compose.konnect.KonnectStyle

object LineDrawer : KonnectDrawer {
    override fun ContentDrawScope.draw(
        p1: KonnectInfo,
        p2: KonnectInfo,
        style: KonnectStyle
    ) {
        drawLine(
            color = style.color,
            start = p1.anchoredPosition(),
            end = p2.anchoredPosition(),
            strokeWidth = style.strokeWidth.toPx()
        )
    }
}