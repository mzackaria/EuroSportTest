package com.mdroid.eurosporttest.di

import com.mdroid.eurosporttest.Constants
import com.mdroid.eurosporttest.remote.GetNewsSorted
import com.mdroid.eurosporttest.remote.NewsApiService
import com.mdroid.eurosporttest.remote.RemoteNewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetNewsSorted(remoteNewsRepository: RemoteNewsRepository): GetNewsSorted {
        return GetNewsSorted(remoteNewsRepository)
    }

    @Provides
    @Singleton
    fun provideRemoteNewsRepo(newsApiService: NewsApiService): RemoteNewsRepository {
        return RemoteNewsRepository(newsApiService)
    }
}