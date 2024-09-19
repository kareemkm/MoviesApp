package com.example.movieapp.viewModel

import com.example.movieapp.models.Detailes
import com.example.movieapp.models.MoviesList
import com.example.movieapp.utils.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMovieList(page:Int) : Response<MoviesList>{
        return RetrofitInstance.api.getMovies(page)
    }
    suspend fun getDetalisById(id:Int):Response<Detailes>{
        return RetrofitInstance.api.getDetailsById(id = id)
    }

}