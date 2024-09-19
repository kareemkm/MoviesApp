package com.example.movieapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Data
import com.example.movieapp.models.Detailes
import kotlinx.coroutines.launch

class MovieViewModels: ViewModel() {


    private val repository = Repository()
    var state by mutableStateOf(ScreenState())
    var id by mutableIntStateOf(0)


    init {
        viewModelScope.launch {
            val response = repository.getMovieList(state.page)
            state = state.copy(
                movies = response.body()!!.data
            )
        }
        getDetailsById()


    }
    fun getDetailsById(){
        viewModelScope.launch {
            try {
                val response = repository.getDetalisById(id = id)
                if (response.isSuccessful){
                    state = state.copy(detailsData = response.body()!!)
                }
            }catch (e:Exception){

            }
        }

    }



}

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1,
    val detailsData:Detailes = Detailes()
)