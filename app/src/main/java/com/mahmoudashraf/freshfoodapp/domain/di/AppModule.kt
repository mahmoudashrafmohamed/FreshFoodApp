package com.mahmoudashraf.freshfoodapp.domain.di

import com.mahmoudashraf.freshfoodapp.data.sources.remote.FreshFoodRemoteDataSourceImpl
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import com.mahmoudashraf.freshfoodapp.domain.interactor.FreshFoodInterActor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFreshFoodInterActor(
        freshFoodRemoteDataSource: FreshFoodRemoteDataSource
    ): FreshFoodInterActor {
        return FreshFoodInterActor(freshFoodRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideFreshFoodRemoteDataSource(): FreshFoodRemoteDataSource {
        return FreshFoodRemoteDataSourceImpl()
    }
}
