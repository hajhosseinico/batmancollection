package ir.hajhosseinico.yarabatmancollection.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListDao
import ir.hajhosseinico.yarabatmancollection.model.room.MovieDatabase
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailDao
import javax.inject.Singleton

/**
 * Provides room dependencies
 * @Singleton is used because we had only 1 scope. singleton scope is = application lifecycle scope
 * If it was a full application, i would provide dependencies into custom scopes (or activity scope)
 */

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideMovieDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context, MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(movieDatabase: MovieDatabase): MovieListDao {
        return movieDatabase.movieListDao()
    }

    @Singleton
    @Provides
    fun provideMovieDetailDAO(movieDatabase: MovieDatabase): MovieDetailDao {
        return movieDatabase.movieDetailDao()
    }
}