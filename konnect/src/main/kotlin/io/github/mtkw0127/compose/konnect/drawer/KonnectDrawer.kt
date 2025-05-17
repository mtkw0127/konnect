package io.github.mtkw0127.compose.konnect.drawer

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import io.github.mtkw0127.compose.konnect.KonnectInfo
import io.github.mtkw0127.compose.konnect.KonnectStyle

interface KonnectDrawer {
    fun ContentDrawScope.draw(
        p1: KonnectInfo,
        p2: KonnectInfo,
        style: KonnectStyle,
    )
}