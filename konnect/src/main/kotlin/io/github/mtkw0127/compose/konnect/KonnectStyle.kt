package io.github.mtkw0127.compose.konnect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mtkw0127.compose.konnect.drawer.ArrowDrawer
import io.github.mtkw0127.compose.konnect.drawer.KonnectDrawer
import io.github.mtkw0127.compose.konnect.drawer.LineDrawer

sealed interface KonnectStyle {
    val color: Color
    val strokeWidth: Dp
    val drawer: KonnectDrawer
    val type: KonnectType

    data class Line(
        override val color: Color,
        override val strokeWidth: Dp,
        override val drawer: KonnectDrawer = LineDrawer,
        override val type: KonnectType = KonnectType.STRAIGHT,
    ) : KonnectStyle {
        companion object {
            val Default = Line(
                color = Color.Black,
                strokeWidth = 1.dp,
            )
        }
    }

    data class Arrow(
        override val color: Color,
        override val strokeWidth: Dp,
        override val drawer: KonnectDrawer = ArrowDrawer,
        override val type: KonnectType = KonnectType.STRAIGHT,
    ) : KonnectStyle
}