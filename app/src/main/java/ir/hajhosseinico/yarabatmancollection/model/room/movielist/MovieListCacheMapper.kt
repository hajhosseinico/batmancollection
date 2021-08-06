package ir.hajhosseinico.yarabatmancollection.model.room.movielist

import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieListNetworkEntity
import javax.inject.Inject

/**
 * This class converts
 *  MovieListCacheEntity (data from database) to MovieListNetworkEntity (data from server)
 *  MovieListNetworkEntity (data from server) to MovieListCacheEntity (data from database)
 */

class MovieListCacheMapper
@Inject
constructor() :
    MovieListEntityMapper<MovieListCacheEntity, MovieListNetworkEntity> {

    override fun mapMovieListToEntity(domainModel: MovieListNetworkEntity): MovieListCacheEntity {
        return MovieListCacheEntity(
            title = domainModel.title,
            year = domainModel.year,
            imdbID = domainModel.imdbID,
            type = domainModel.type,
            poster = domainModel.poster,
        )
    }

    fun mapFromEntityList(entities: List<MovieListCacheEntity>): List<MovieListNetworkEntity> {
        return entities.map { movieList ->
            mapMovieListFromEntity(movieList)
        }
    }

    override fun mapMovieListFromEntity(entity: MovieListCacheEntity): MovieListNetworkEntity {
        return MovieListNetworkEntity(
            title = entity.title,
            year = entity.year,
            imdbID = entity.imdbID,
            type = entity.type,
            poster = entity.poster,
        )
    }

}
