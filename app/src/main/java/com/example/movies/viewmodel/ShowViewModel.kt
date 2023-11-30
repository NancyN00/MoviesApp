package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.repository.GetShowsRepository
import com.example.movies.util.Resource
import com.example.movies.util.ShowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val showsRepository: GetShowsRepository
): ViewModel(){

    private val _state = MutableStateFlow(ShowState())
    val state = _state.asStateFlow()

    init {
        getShows()
    }

    private fun getShows() {
        viewModelScope.launch {
            showsRepository.getShows().collect{result ->

                when(result){
                    is Resource.Loading ->{
                        _state.value = ShowState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _state.value = ShowState(
                            shows = result.data?: emptyList()
                        )
                    }

                    is Resource.Error ->{
                        _state.value = ShowState(
                            message = result.message ?: "Something is wrong"
                        )
                    }
                }

            }
        }
    }


}