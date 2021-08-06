package ir.hajhosseinico.yarabatmancollection.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hajhosseinico.yarabatmancollection.model.retrofit.MovieRetrofitInterface
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListDao
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListCacheMapper
import ir.hajhosseinico.yarabatmancollection.repository.MovieListRepository
import ir.hajhosseinico.yarabatmancollection.util.InternetStatus
import javax.inject.Singleton

/**
 * MovieDetailRepositoryModule will provide all the dependencies that MovieListRepository requires
 * Retrofit interface and internet status are instantiated in RetrofitModule
 * MovieDao and MovieListCacheMapper are instantiated in RoomModule
 */

@InstallIn(SingletonComponent::class)
@Module
object MovieListRepositoryModule {
    @Singleton
    @Provides
    fun provideMovieListRepository(
        movieDao: MovieListDao,
        retrofitInterface: MovieRetrofitInterface,
        cacheMapper: MovieListCacheMapper,
        internetStatus: InternetStatus
    ): MovieListRepository{
        return MovieListRepository(movieDao,retrofitInterface,cacheMapper,internetStatus)
    }
}