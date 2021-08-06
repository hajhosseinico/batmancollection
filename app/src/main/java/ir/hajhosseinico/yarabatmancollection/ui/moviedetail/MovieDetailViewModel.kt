package ir.hajhosseinico.yarabatmancollection.ui.moviedetail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieDetailNetworkEntity
import ir.hajhosseinico.yarabatmancollection.repository.MovieDetailRepository
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * MovieDetailViewModel
 */
@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val movieDetailRepository: MovieDetailRepository,
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<MovieDetailNetworkEntity>> =
        MutableLiveData()

    val dataState: LiveData<DataState<MovieDetailNetworkEntity>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MovieDetailStateEvent, apiKey:String,imdbId: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MovieDetailStateEvent.GetMovieDetail -> {
                    // getting data from repository and passing it to fragment
                    movieDetailRepository.getMovies(apiKey, imdbId)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MovieDetailStateEvent.None -> {
                    // Do Nothing
                }
            }
        }
    }
}

sealed class MovieDetailStateEvent {
    object GetMovieDetail : MovieDetailStateEvent()
    object None : MovieDetailStateEvent()
}

