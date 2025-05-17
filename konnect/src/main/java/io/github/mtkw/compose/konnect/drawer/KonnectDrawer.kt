package io.github.mtkw.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw.compose.konnect.KonnectInfo
import io.github.mtkw.compose.konnect.KonnectStyle

interface KonnectDrawer {
    fun ContentDrawScope.draw(
        p1: KonnectInfo,
        p2: KonnectInfo,
        style: KonnectStyle,
    )
}