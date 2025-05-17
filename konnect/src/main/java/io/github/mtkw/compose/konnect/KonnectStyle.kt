package io.github.mtkw.compose.konnect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface KonnectStyle {
    val color: Color
    val strokeWidth: Dp

    data class Line(
        override val color: Color,
        override val strokeWidth: Dp,
    ) : KonnectStyle {
        companion object {
            val Default = Line(
                color = Color.Black,
                strokeWidth = 1.dp,
            )
        }
    }
}