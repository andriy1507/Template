package com.spaceapps.template.core.ui.activity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class MainActivityUiEventDispatcherImpl
@Inject
constructor() : MainActivityUiEventDispatcher {
    private val coroutineScope = CoroutineScope(EmptyCoroutineContext)
    private val _emitter = MutableSharedFlow<MainActivityUiEvent>()
    override val emitter: SharedFlow<MainActivityUiEvent> get() = _emitter.asSharedFlow()

    override fun emit(event: MainActivityUiEvent) {
        coroutineScope.launch { _emitter.emit(event) }
    }
}
