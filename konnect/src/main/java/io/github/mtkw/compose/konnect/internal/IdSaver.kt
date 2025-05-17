package io.github.mtkw.compose.konnect.internal

import androidx.compose.runtime.saveable.Saver
import io.github.mtkw.compose.konnect.KonnectState

val idSaver = Saver<KonnectState.Id, String>(
    save = { it.value },
    restore = { KonnectState.Id(it) }
)