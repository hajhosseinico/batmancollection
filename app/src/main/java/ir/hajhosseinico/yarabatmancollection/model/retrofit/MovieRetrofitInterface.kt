package ir.hajhosseinico.yarabatmancollection.model.retrofit

import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieDetailNetworkEntity
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.SearchNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api queries
 * used by retrofit
 */
interface MovieRetrofitInterface {
    @GET("/")
    suspend fun getMovieList(@Query("apikey") p1: String , @Query("s") p2: String ): SearchNetworkEntity
    @GET("/")
    suspend fun getMovieDetail(@Query("apikey") p1: String , @Query("i") p2: String ): MovieDetailNetworkEntity
}