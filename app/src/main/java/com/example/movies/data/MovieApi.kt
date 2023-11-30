package com.example.movies.data

import retrofit2.http.GET

interface MovieApi {

    @GET("shows")
    suspend fun getShows() : List<Shows>
}