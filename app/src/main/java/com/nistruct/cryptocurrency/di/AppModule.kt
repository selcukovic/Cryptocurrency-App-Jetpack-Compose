package com.nistruct.cryptocurrency.di

import com.nistruct.cryptocurrency.BuildConfig
import com.nistruct.cryptocurrency.common.Constants.BASE_URL
import com.nistruct.cryptocurrency.data.remote.CoinPaprikaApi
import com.nistruct.cryptocurrency.data.repository.CoinRepositoryImpl
import com.nistruct.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else{
        OkHttpClient
            .Builder()
            .build()
    }
    @Singleton
    @Provides
    @Named("retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun proviveCoinPaprikaApi(@Named("retrofit") retrofit: Retrofit) : CoinPaprikaApi{
        return retrofit.create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinPaprikaApi) : CoinRepository = CoinRepositoryImpl(api)
}