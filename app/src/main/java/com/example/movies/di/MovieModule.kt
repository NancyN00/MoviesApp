package com.example.movies.di

import android.text.method.MovementMethod
import com.example.movies.data.MovieApi
import com.example.movies.repository.GetShowsImplRepository
import com.example.movies.repository.GetShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.tvmaze.com/"
@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton

    fun provideRetrofit() : MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(api: MovieApi) : GetShowsRepository{
        return GetShowsImplRepository(api)
    }

}