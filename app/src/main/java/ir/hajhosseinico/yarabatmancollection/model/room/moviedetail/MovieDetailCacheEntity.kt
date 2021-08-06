package ir.hajhosseinico.yarabatmancollection.model.room.moviedetail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room movie detail table object model
 * Object could be null but i filled every null string with "N/A" for better user experience
 * So if server does not send a string property, i will replace it with "N/A" in MovieDetailCacheMapper class
 */

@Entity(tableName = "movie_detail")
data class MovieDetailCacheEntity(

    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "year")
    var year: String?,
    @ColumnInfo(name = "rated")
    var rated: String?,
    @ColumnInfo(name = "released")
    var released: String?,
    @ColumnInfo(name = "runtime")
    var runtime: String?,
    @ColumnInfo(name = "genre")
    var genre: String?,
    @ColumnInfo(name = "director")
    var director: String?,
    @ColumnInfo(name = "writer")
    var writer: String?,
    @ColumnInfo(name = "actors")
    var actors: String?,
    @ColumnInfo(name = "plot")
    var plot: String?,
    @ColumnInfo(name = "language")
    var language: String?,
    @ColumnInfo(name = "country")
    var country: String?,
    @ColumnInfo(name = "awards")
    var awards: String?,
    @ColumnInfo(name = "poster")
    var poster: String?,
    @ColumnInfo(name = "ratings")
    var ratings: String?,
    @ColumnInfo(name = "metascore")
    var metascore: String?,
    @ColumnInfo(name = "imdbrating")
    var imdbrating: String?,
    @ColumnInfo(name = "imdbvotes")
    var imdbvotes: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imdbid")
    var imdbid: String,
    @ColumnInfo(name = "type")
    var type: String?,
    @ColumnInfo(name = "dvd")
    var dvd: String?="",
    @ColumnInfo(name = "boxoffice")
    var boxoffice: String?,
    @ColumnInfo(name = "production")
    var production: String?,
    @ColumnInfo(name = "website")
    var website: String?,
    @ColumnInfo(name = "response")
    var response: String?,
)
