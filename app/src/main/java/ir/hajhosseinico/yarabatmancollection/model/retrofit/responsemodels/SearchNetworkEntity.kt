package ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Null safe api response object (in the sample api, some of the fields are null or server is not sending them)
 */
data class SearchNetworkEntity(
    @SerializedName("Search")
    @Expose
    var search: List<MovieListNetworkEntity>,
    @SerializedName("totalResults")
    @Expose
    var totalResults: String,

    @SerializedName("Response")
    @Expose
    var Response: String

)