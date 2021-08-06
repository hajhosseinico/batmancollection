package ir.hajhosseinico.yarabatmancollection.model.room.movielist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Room movie list table object model
 */
@Entity(tableName = "movie_list")
data class MovieListCacheEntity(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imdbID")
    var imdbID: String,

    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "poster")
    var poster: String,
    )