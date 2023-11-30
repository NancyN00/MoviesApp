package com.example.movies.repository

import com.example.movies.data.MovieApi
import com.example.movies.data.Shows
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShowsImplRepository @Inject constructor(
    private val api: MovieApi
) : GetShowsRepository {
    override fun getShows(): Flow<Resource<List<Shows>>> = flow{

        try {
            emit(Resource.Loading(data = null))

            emit(Resource.Success(data = api.getShows()))

        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Something went wrong"))

        }catch (e: IOException){
            emit(Resource.Error(message = "network not connected"))
        }

    }
}