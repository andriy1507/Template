package com.spaceapps.template.core.ui.activity

import kotlinx.coroutines.flow.SharedFlow

interface MainActivityUiEventDispatcher {
    val emitter: SharedFlow<MainActivityUiEvent>

    fun emit(event: MainActivityUiEvent)
}
