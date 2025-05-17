package io.github.mtkw.compose.konnect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mtkw.compose.konnect.drawer.KonnectDrawer
import io.github.mtkw.compose.konnect.drawer.LineDrawer

sealed interface KonnectStyle {
    val color: Color
    val strokeWidth: Dp
    val drawer: KonnectDrawer

    data class Line(
        override val color: Color,
        override val strokeWidth: Dp,
        override val drawer: KonnectDrawer = LineDrawer,
    ) : KonnectStyle {
        companion object {
            val Default = Line(
                color = Color.Black,
                strokeWidth = 1.dp,
            )
        }
    }
}