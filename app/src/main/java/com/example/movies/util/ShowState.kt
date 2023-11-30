package com.example.movies.util

import com.example.movies.data.Shows

data class ShowState(
    val isLoading: Boolean = false,
    val shows: List<Shows> = emptyList(),
    val message: String = ""
)
