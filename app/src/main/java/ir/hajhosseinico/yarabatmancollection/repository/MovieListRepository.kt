package ir.hajhosseinico.yarabatmancollection.repository

import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieListNetworkEntity
import ir.hajhosseinico.yarabatmancollection.model.retrofit.MovieRetrofitInterface
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListDao
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListCacheMapper
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState
import ir.hajhosseinico.yarabatmancollection.util.InternetStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

/**
 * This class is responsible of getting data of MovieList
 * First loading will be called
 * Second internet availability will be checked
 * If internet is available, we will try to get data and passing it to view model by emitting it
 * If error happens, Error will be emitted to view model
 * If internet is not available, we  try to get data from cache
 * If data is retrieved from cache: dataState.isFromCache = true
 * else: error would be emitted to view model
 */
class MovieListRepository
constructor(
    private val movieDao: MovieListDao,
    private val movieRetrofitInterface: MovieRetrofitInterface,
    private val cacheMapper: MovieListCacheMapper,
    private val internetStatus: InternetStatus,
) {
    suspend fun getMovies(
        apiKey: String,
        name: String
    ): Flow<DataState<List<MovieListNetworkEntity>>> =
        flow {
            emit(DataState.Loading)

            // checking internet availability
            if (internetStatus.isInternetAvailable()) {
                try {
                    // getting data from server
                    val baseNetworkMovies = movieRetrofitInterface.getMovieList(apiKey, name)
                    val movies = baseNetworkMovies.search
                    // adding data to database
                    for (movie in movies) {
                        movieDao.insert(cacheMapper.mapMovieListToEntity(movie))
                    }
                    val cachedMovies = movieDao.get()
                    emit(DataState.Success(cacheMapper.mapFromEntityList(cachedMovies)))
                } catch (e: Exception) {
                    emit(DataState.Error(e))
                }
            } else {
                try {
                    // trying to get data from cache
                    val cachedMovies = movieDao.get()
                    val dataState = DataState.Success(cacheMapper.mapFromEntityList(cachedMovies))
                    dataState.isFromCache = true
                    emit(dataState)
                } catch (e: Exception) {
                    emit(DataState.Error(e))
                }
            }
        }
}
