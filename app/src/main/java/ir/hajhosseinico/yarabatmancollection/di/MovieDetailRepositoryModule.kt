package ir.hajhosseinico.yarabatmancollection.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hajhosseinico.yarabatmancollection.model.retrofit.MovieRetrofitInterface
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailCacheMapper
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailDao
import ir.hajhosseinico.yarabatmancollection.repository.MovieDetailRepository
import ir.hajhosseinico.yarabatmancollection.util.InternetStatus
import javax.inject.Singleton

/**
 * MovieDetailRepositoryModule will provide all the dependencies that MovieDetailRepository requires
 * Retrofit interface and internet status are instantiated in RetrofitModule
 * MovieDao and MovieDetailCacheMapper are instantiated in RoomModule
 */

@InstallIn(SingletonComponent::class)
@Module
object MovieDetailRepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        movieDetailDao: MovieDetailDao,
        retrofitInterface: MovieRetrofitInterface,
        cacheMapper: MovieDetailCacheMapper,
        internetStatus:InternetStatus,
    ): MovieDetailRepository{
        return MovieDetailRepository(movieDetailDao ,retrofitInterface,cacheMapper,internetStatus)
    }
}