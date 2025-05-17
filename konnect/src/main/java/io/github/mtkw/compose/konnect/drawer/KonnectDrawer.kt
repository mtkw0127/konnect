package io.github.mtkw.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw.compose.konnect.KonnectInfo
import io.github.mtkw.compose.konnect.KonnectStyle

interface KonnectDrawer {
    fun ContentDrawScope.draw(
        start: KonnectInfo,
        end: KonnectInfo,
        style: KonnectStyle,
    )
}