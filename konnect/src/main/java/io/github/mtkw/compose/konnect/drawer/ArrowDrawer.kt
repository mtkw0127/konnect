package io.github.mtkw.compose.konnect.drawer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.dp
import io.github.mtkw.compose.konnect.KonnectInfo
import io.github.mtkw.compose.konnect.KonnectInfo.PointRole
import io.github.mtkw.compose.konnect.KonnectStyle
import kotlin.math.cos
import kotlin.math.sin

object ArrowDrawer : KonnectDrawer {
    override fun ContentDrawScope.draw(
        p1: KonnectInfo,
        p2: KonnectInfo,
        style: KonnectStyle
    ) {
        drawLine(
            color = style.color,
            start = p1.anchoredPosition(),
            end = p2.anchoredPosition(),
            strokeWidth = style.strokeWidth.toPx(),
        )

        when {
            p1.pointRole == PointRole.Start && p2.pointRole == PointRole.End -> {
                val start = p1.anchoredPosition()
                val end = p2.anchoredPosition()
                drawArrowHead(start = start, end = end, style = style)
            }

            p1.pointRole == PointRole.End && p2.pointRole == PointRole.Start -> {
                val start = p2.anchoredPosition()
                val end = p1.anchoredPosition()
                drawArrowHead(start = start, end = end, style = style)
            }

            p1.pointRole == PointRole.Both && p2.pointRole == PointRole.Both -> {
                drawArrowHead(
                    start = p1.anchoredPosition(),
                    end = p2.anchoredPosition(),
                    style = style
                )
                drawArrowHead(
                    start = p2.anchoredPosition(),
                    end = p1.anchoredPosition(),
                    style = style
                )
            }

            else -> {
                throw IllegalArgumentException("Invalid point roles: ${p1.pointRole}, ${p2.pointRole}")
            }
        }
    }

    private fun ContentDrawScope.drawArrowHead(
        start: Offset,
        end: Offset,
        style: KonnectStyle,
    ) {
        val strokeWidth = style.strokeWidth.toPx()
        val direction = (start - end).normalize()
        val distance = (end - start).getDistance()

        // 左右の羽根の向き
        val maxArrowHeadLength = 15.dp.toPx()
        val arrowHeadLength = minOf(distance * 0.5f, maxArrowHeadLength)
        val leftWing =
            end + direction.rotate(Math.toRadians(12.5).toFloat()).multiply(arrowHeadLength)
        val rightWing = end + direction.rotate(Math.toRadians(-12.5).toFloat())
            .multiply(arrowHeadLength)

        // 羽根を描画
        drawLine(
            color = style.color,
            start = end,
            end = leftWing,
            strokeWidth = strokeWidth
        )
        drawLine(
            color = style.color,
            start = end,
            end = rightWing,
            strokeWidth = strokeWidth
        )
    }
}

private fun Offset.multiply(scalar: Float): Offset {
    return Offset(x * scalar, y * scalar)
}

private fun Offset.normalize(): Offset {
    val length = this.getDistance()
    return if (length != 0f) Offset(x / length, y / length) else Offset.Zero
}

private fun Offset.rotate(angleRad: Float): Offset {
    val cosA = cos(angleRad)
    val sinA = sin(angleRad)
    return Offset(
        x * cosA - y * sinA,
        x * sinA + y * cosA
    )
}
