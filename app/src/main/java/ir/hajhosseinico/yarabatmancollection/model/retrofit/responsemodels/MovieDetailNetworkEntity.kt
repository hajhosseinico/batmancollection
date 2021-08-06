package ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Null safe api response object (in the sample api, some of the fields are null or server is not sending them)
 */

data class MovieDetailNetworkEntity(
    @SerializedName("Title")
    @Expose
    var title: String?,

    @SerializedName("Year")
    @Expose
    var year: String?,

    @SerializedName("Rated")
    @Expose
    var rated: String?,

    @SerializedName("Released")
    @Expose
    var released: String?,

    @SerializedName("Runtime")
    @Expose
    var runtime: String?,

    @SerializedName("Genre")
    @Expose
    var genre: String?,
    @SerializedName("Director")
    @Expose
    var director: String?,
    @SerializedName("Writer")
    @Expose
    var writer: String?,
    @SerializedName("Actors")
    @Expose
    var actors: String?,
    @SerializedName("Plot")
    @Expose
    var plot: String?,
    @SerializedName("Language")
    @Expose
    var language: String?,
    @SerializedName("Country")
    @Expose
    var country: String?,
    @SerializedName("Awards")
    @Expose
    var awards: String?,
    @SerializedName("Poster")
    @Expose
    var poster: String?,
    @SerializedName("Ratings")
    @Expose
    var ratings: List<RatingNetworkEntity>,
    @SerializedName("Metascore")
    @Expose
    var metascore: String?,
    @SerializedName("imdbRating")
    @Expose
    var imdbrating: String?,
    @SerializedName("imdbVotes")
    @Expose
    var imdbvotes: String?,
    @SerializedName("imdbID")
    @Expose
    var imdbid: String,
    @SerializedName("Type")
    @Expose
    var type: String?,
    @SerializedName("DVD")
    @Expose
    var dvd: String? ,
    @SerializedName("BoxOffice")
    @Expose
    var boxoffice: String?,
    @SerializedName("Production")
    @Expose
    var production: String?,
    @SerializedName("Website")
    @Expose
    var website: String?,
    @SerializedName("Response")
    @Expose
    var response: String,
)