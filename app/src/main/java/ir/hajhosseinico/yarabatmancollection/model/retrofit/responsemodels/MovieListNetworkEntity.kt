package ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Null safe api response object (in the sample api, some of the fields are null or server is not sending them)
 */

data class MovieListNetworkEntity(
    @SerializedName("Title")
    @Expose
    var title: String,

    @SerializedName("Year")
    @Expose
    var year: String,

    @SerializedName("imdbID")
    @Expose
    var imdbID: String,

    @SerializedName("Type")
    @Expose
    var type: String,

    @SerializedName("Poster")
    @Expose
    var poster: String,


    )