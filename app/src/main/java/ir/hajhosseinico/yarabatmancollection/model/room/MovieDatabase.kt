package ir.hajhosseinico.yarabatmancollection.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailCacheEntity
import ir.hajhosseinico.yarabatmancollection.model.room.moviedetail.MovieDetailDao
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListCacheEntity
import ir.hajhosseinico.yarabatmancollection.model.room.movielist.MovieListDao

/**
 * Instantiating room database and setting database name
 * Used by Provider Modules
 */
@Database(entities = [MovieListCacheEntity::class, MovieDetailCacheEntity::class],version = 1)
abstract class MovieDatabase : RoomDatabase (){
    abstract fun movieListDao(): MovieListDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object{
        const val DATABASE_NAME = "movie_db"
    }
}