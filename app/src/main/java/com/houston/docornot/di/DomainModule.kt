package com.houston.docornot.di

import com.houston.docornot.data.impl.RepositoryImpl
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.domain.api.Interactor
import com.houston.docornot.domain.api.Repository
import com.houston.docornot.domain.impl.InteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideInteractor(repository: Repository): Interactor {
        return InteractorImpl(repository = repository)
    }

    @Provides
    fun provideRepository(client: NetworkClient): Repository {
        return RepositoryImpl(client = client)
    }

}