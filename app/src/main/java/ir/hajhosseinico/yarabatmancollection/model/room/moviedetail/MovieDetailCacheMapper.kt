package ir.hajhosseinico.yarabatmancollection.model.room.moviedetail

import com.google.gson.Gson
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieDetailNetworkEntity
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.RatingNetworkEntity
import javax.inject.Inject

/**
 * This class converts
 *  MovieDetailCacheEntity (data from database) to MovieDetailNetworkEntity (data from server)
 *  MovieDetailNetworkEntity (data from server) to MovieDetailCacheEntity (data from database)
 *  And fill every null string with "N/A" for better user experience
 */

class MovieDetailCacheMapper
@Inject
constructor(
    private val gson: Gson,
) :
    MovieDetailEntityMapper<MovieDetailCacheEntity, MovieDetailNetworkEntity> {

    override fun mapMovieDetailFromEntity(entity: MovieDetailCacheEntity): MovieDetailNetworkEntity {
        return MovieDetailNetworkEntity(
            title = entity.title?:"N/A",
            year = entity.year?:"N/A",
            rated = entity.rated?:"N/A",
            released = entity.released?:"N/A",
            runtime = entity.runtime?:"N/A",
            genre = entity.genre?:"N/A",
            director = entity.director?:"N/A",
            writer = entity.writer?:"N/A",
            actors = entity.actors?:"N/A",
            plot = entity.plot?:"N/A",
            language = entity.language?:"N/A",
            country = entity.country?:"N/A",
            awards = entity.awards?:"N/A",
            poster = entity.poster?:"N/A",
            ratings = getRatingListObjectFromJson(entity.ratings?:"N/A"),
            metascore = entity.metascore?:"N/A",
            imdbrating = entity.imdbrating?:"N/A",
            imdbvotes = entity.imdbvotes?:"N/A",
            imdbid = entity.imdbid ,
            type = entity.type?:"N/A",
            dvd = entity.dvd?:"N/A",
            boxoffice = entity.boxoffice?:"N/A",
            production = entity.production?:"N/A",
            website = entity.website?:"N/A",
            response = entity.response?:"N/A",
        )
    }

    override fun mapMovieDetailToEntity(domainModel: MovieDetailNetworkEntity): MovieDetailCacheEntity {
        return MovieDetailCacheEntity(
            title = domainModel.title?:"N/A",
            year = domainModel.year?:"N/A",
            rated = domainModel.rated?:"N/A",
            released = domainModel.released?:"N/A",
            runtime = domainModel.runtime?:"N/A",
            genre = domainModel.genre?:"N/A",
            director = domainModel.director?:"N/A",
            writer = domainModel.writer?:"N/A",
            actors = domainModel.actors?:"N/A",
            plot = domainModel.plot?:"N/A",
            language = domainModel.language?:"N/A",
            country = domainModel.country?:"N/A",
            awards = domainModel.awards?:"N/A",
            poster = domainModel.poster?:"N/A",
            ratings = getJsonObjectFromArray(domainModel.ratings),
            metascore = domainModel.metascore?:"N/A",
            imdbrating = domainModel.imdbrating?:"N/A",
            imdbvotes = domainModel.imdbvotes?:"N/A",
            imdbid = domainModel.imdbid,
            type = domainModel.type?:"N/A",
            boxoffice = domainModel.boxoffice?:"N/A",
            production = domainModel.production?:"N/A",
            website = domainModel.website?:"N/A",
            response = domainModel.response?:"N/A",
            dvd = domainModel.dvd?:"N/A",
        )
    }

    private fun getRatingListObjectFromJson(jsonStr: String): List<RatingNetworkEntity> {
        return gson.fromJson(jsonStr, Array<RatingNetworkEntity>::class.java).toList()
    }

    private fun getJsonObjectFromArray(ratingNetworkEntity: List<RatingNetworkEntity>): String {
        return gson.toJson(ratingNetworkEntity)
    }

}
