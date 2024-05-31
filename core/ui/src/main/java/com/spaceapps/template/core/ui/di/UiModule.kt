package com.spaceapps.template.core.ui.di

import com.spaceapps.template.core.ui.activity.MainActivityUiEventDispatcher
import com.spaceapps.template.core.ui.activity.MainActivityUiEventDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UiModule {
    @Binds
    fun bindMainActivityUiEventDispatcher(impl: MainActivityUiEventDispatcherImpl): MainActivityUiEventDispatcher
}
