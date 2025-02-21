package com.houston.docornot.di

import android.content.Context
import com.houston.docornot.data.network.ApiService
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.data.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

}