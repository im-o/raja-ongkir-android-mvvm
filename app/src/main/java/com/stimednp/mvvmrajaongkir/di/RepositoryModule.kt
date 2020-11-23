package com.stimednp.mvvmrajaongkir.di

import com.stimednp.mvvmrajaongkir.data.network.ApiRajaOngkir
import com.stimednp.mvvmrajaongkir.data.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by rivaldy on Nov/15/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesDataRepository(apiRajaOngkir: ApiRajaOngkir):DataRepository {
        return DataRepository(apiRajaOngkir)
    }
}