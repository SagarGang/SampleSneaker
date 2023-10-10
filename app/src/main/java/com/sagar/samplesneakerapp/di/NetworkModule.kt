package com.sagar.samplesneakerapp.di

import com.sagar.samplesneakerapp.BuildConfig
import com.sagar.samplesneakerapp.repo.SneakerRepository
import com.sagar.samplesneakerapp.repo.SneakerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    private fun getLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.google.com/")
            .client(OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): SneakerApi {
        return retrofit.create(SneakerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSneakerRepo(sneakerApi: SneakerApi): SneakerRepository {
        return SneakerRepositoryImpl(sneakerApi)
    }
}