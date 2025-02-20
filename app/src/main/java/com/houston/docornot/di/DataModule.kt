package com.houston.docornot.di

import android.content.Context
import com.houston.docornot.data.impl.RepositoryImpl
import com.houston.docornot.data.network.ApiService
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.data.network.RetrofitNetworkClient
import com.houston.docornot.domain.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideNetworkClient(
        @ApplicationContext context: Context,
        service: ApiService
    ): NetworkClient {
        return RetrofitNetworkClient(
            context = context,
            service = service
        )
    }

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://houston.onlyoffice.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(client: NetworkClient): Repository {
        return RepositoryImpl(client = client)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}