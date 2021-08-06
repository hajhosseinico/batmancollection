package ir.hajhosseinico.yarabatmancollection.ui.movielist

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieListNetworkEntity
import ir.hajhosseinico.yarabatmancollection.repository.MovieListRepository
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * MovieListViewModel
 */
@HiltViewModel
class MovieListViewModel
@Inject
constructor(
    private val movieListRepository: MovieListRepository,
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<MovieListNetworkEntity>>> =
        MutableLiveData()

    val dataState: LiveData<DataState<List<MovieListNetworkEntity>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MovieListStateEvent, apiKey: String, name: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MovieListStateEvent.GetMovies -> {
                    // getting data from repository and passing it to fragment
                    movieListRepository.getMovies(apiKey, name)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MovieListStateEvent.None -> {
                    // Do Nothing
                }
            }
        }
    }
}

sealed class MovieListStateEvent {
    object GetMovies : MovieListStateEvent()
    object None : MovieListStateEvent()
}

