package io.github.mtkw0127.compose.konnect.internal

import androidx.compose.runtime.saveable.Saver
import io.github.mtkw0127.compose.konnect.KonnectState

internal val idSaver = Saver<KonnectState.Id, String>(
    save = { it.value },
    restore = { KonnectState.Id(it) }
)