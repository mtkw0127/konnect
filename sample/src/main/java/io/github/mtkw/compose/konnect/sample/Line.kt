package io.github.mtkw.compose.konnect.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mtkw.compose.konnect.KonnectInfo
import io.github.mtkw.compose.konnect.KonnectStyle
import io.github.mtkw.compose.konnect.drawKonnect
import io.github.mtkw.compose.konnect.konnect
import io.github.mtkw.compose.konnect.rememberKonnectState

@Composable
@Preview(showBackground = true)
fun LineRow() {
    val state = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Red,
            strokeWidth = 2.dp,
        )
    )
    Row(modifier = Modifier.drawKonnect(state)) {
        Text(
            text = "Item 1-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = KonnectInfo.RectAnchor.CenterEnd
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "Item 1-2",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = KonnectInfo.RectAnchor.CenterStart,
                )
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LineColum() {
    val state = rememberKonnectState(
        style = KonnectStyle.Line(
            color = Color.Blue,
            strokeWidth = 1.dp,
        )
    )
    Column(modifier = Modifier.drawKonnect(state)) {
        Text(
            text = "Item 2-1",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = KonnectInfo.RectAnchor.BottomCenter
                )
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Item 2-2",
            modifier = Modifier
                .konnect(
                    state = state,
                    anchor = KonnectInfo.RectAnchor.TopCenter,
                )
                .padding(10.dp)
        )
    }
}