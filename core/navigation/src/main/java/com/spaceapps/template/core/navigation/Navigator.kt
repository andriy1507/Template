package com.spaceapps.template.core.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationEvent = (NavHostController) -> Unit

interface Navigator {
    val emitter: SharedFlow<NavigationEvent>

    fun emit(event: NavigationEvent)
}
