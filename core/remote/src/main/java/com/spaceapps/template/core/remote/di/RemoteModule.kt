package com.spaceapps.template.core.remote.di

import com.spaceapps.template.core.remote.api.CatApi
import com.spaceapps.template.core.remote.api.CatApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {
    @Binds
    fun bindCatApi(impl: CatApiImpl): CatApi
}
