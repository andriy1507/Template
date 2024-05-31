package com.spaceapps.template.core.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class NavigatorImpl
    @Inject
    constructor() : Navigator {
        private val coroutineScope = CoroutineScope(EmptyCoroutineContext)
        private val _emitter = MutableSharedFlow<NavigationEvent>()
        override val emitter: SharedFlow<NavigationEvent> get() = _emitter.asSharedFlow()

        override fun emit(event: NavigationEvent) {
            coroutineScope.launch { _emitter.emit(event) }
        }
    }
