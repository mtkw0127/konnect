package io.github.mtkw0127.compose.konnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class KonnectState(
    val style: KonnectStyle,
    val points: MutableMap<Id, KonnectInfo> = mutableMapOf(),
) {
    data class Id(val value: String)
}

@Composable
fun rememberKonnectState(
    style: KonnectStyle = KonnectStyle.Line.Default,
): KonnectState = remember {
    return@remember KonnectState(
        style = style,
    )
}