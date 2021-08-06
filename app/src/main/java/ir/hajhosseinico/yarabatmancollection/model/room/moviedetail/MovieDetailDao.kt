package ir.hajhosseinico.yarabatmancollection.model.room.moviedetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Movie detail room Dao
 */
@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetailCacheEntity: MovieDetailCacheEntity): Long

    @Query("SELECT * FROM movie_detail WHERE imdbid ==:imdbid_")
    suspend fun get(imdbid_:String): MovieDetailCacheEntity
}