package com.example.movies.repository

import com.example.movies.data.Shows
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetShowsRepository {

    fun getShows() : Flow<Resource<List<Shows>>>
}