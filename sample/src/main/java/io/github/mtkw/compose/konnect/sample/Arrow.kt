package io.github.mtkw.compose.konnect.sample

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mtkw.compose.konnect.KonnectInfo.PointRole
import io.github.mtkw.compose.konnect.KonnectInfo.RectAnchor
import io.github.mtkw.compose.konnect.KonnectStyle
import io.github.mtkw.compose.konnect.drawKonnect
import io.github.mtkw.compose.konnect.konnect
import io.github.mtkw.compose.konnect.rememberKonnectState

@Composable
@Preview(showBackground = true)
fun ArrowOneSide() {
    val state = rememberKonnectState(
        style = KonnectStyle.Arrow(
            color = Color.Red,
            strokeWidth = 2.dp,
        )
    )
    Row(
        modifier = Modifier
            .height(50.dp)
            .drawKonnect(state)
    ) {
        Text(
            text = "Item 1-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterEnd,
                    pointRole = PointRole.Start,
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))

        Text(
            text = "Item 1-2",
            modifier = Modifier
                .offset(y = 20.dp)
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterStart,
                    pointRole = PointRole.End
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ArrowBoth() {
    val state = rememberKonnectState(
        style = KonnectStyle.Arrow(
            color = Color.Red,
            strokeWidth = 2.dp,
        )
    )
    Row(
        modifier = Modifier
            .height(50.dp)
            .drawKonnect(state)
    ) {
        Text(
            text = "Item 1-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterEnd,
                    pointRole = PointRole.Both,
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))

        Text(
            text = "Item 1-2",
            modifier = Modifier
                .offset(y = 20.dp)
                .konnect(
                    state = state,
                    anchor = RectAnchor.CenterStart,
                    pointRole = PointRole.Both
                )
                .padding(10.dp)
        )
    }
}