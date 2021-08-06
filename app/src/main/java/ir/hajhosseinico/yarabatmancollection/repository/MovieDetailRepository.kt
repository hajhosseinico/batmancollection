package ir.hajhosseinico.yarabatmancollection.repository

import ir.hajhosseinico.yarabatmancollection.model.retrofit.MovieRetrofitInterface
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieDetailNetworkEntity
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailCacheMapper
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailDao
import ir.hajhosseinico.yarabatmancollection.util.InternetStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * This class is responsible of getting data of MovieDetail
 * First loading will be called
 * Second internet availability will be checked
 * If internet is available, we will try to get data and passing it to view model by emitting it
 * If error happens, Error will be emitted to view model
 * If internet is not available, we  try to get data from cache
 * If data is retrieved from cache: dataState.isFromCache = true
 * else: error would be emitted to view model
 */
class MovieDetailRepository
constructor(
    private val movieDetailDao: MovieDetailDao,
    private val movieRetrofitInterface: MovieRetrofitInterface,
    private val cacheMapper: MovieDetailCacheMapper,
    private val internetStatus: InternetStatus,
) {

    suspend fun getMovies(
        apiKey: String,
        imdbID: String
    ): Flow<DataState<MovieDetailNetworkEntity>> =
        flow {
            emit(DataState.Loading)

            // checking internet availability
            if (internetStatus.isInternetAvailable()) {
                try {
                    // getting data from server
                    val baseNetworkMovies = movieRetrofitInterface.getMovieDetail(apiKey, imdbID)
                    val movieDetailCacheEntity =
                        cacheMapper.mapMovieDetailToEntity(baseNetworkMovies)
                    // adding data to database
                    movieDetailDao.insert(movieDetailCacheEntity)
                    val cachedMovies = movieDetailDao.get(imdbID)
                    emit(DataState.Success(cacheMapper.mapMovieDetailFromEntity(cachedMovies)))
                } catch (e: Exception) {
                    emit(DataState.Error(e))
                }
            } else {
                try {
                    // trying to get data from cache
                    val cachedMovies = movieDetailDao.get(imdbID)
                    val dataState =
                        DataState.Success(cacheMapper.mapMovieDetailFromEntity(cachedMovies))
                    dataState.isFromCache = true
                    emit(dataState)
                } catch (e: Exception) {
                    emit(DataState.Error(e))
                }
            }


        }
}
