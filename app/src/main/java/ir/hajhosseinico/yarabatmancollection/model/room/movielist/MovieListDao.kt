package ir.hajhosseinico.yarabatmancollection.model.room.movielist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Movie list room Dao
 */
@Dao
interface MovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieListEntity: MovieListCacheEntity): Long

    @Query("SELECT * FROM movie_list")
    suspend fun get(): List<MovieListCacheEntity>
}