package ir.hajhosseinico.yarabatmancollection.model.room.movielist

/**
 * Movie list entity mapper interface
 */
interface MovieListEntityMapper<Entity, DomainModel> {
    fun mapMovieListFromEntity(entity:Entity) : DomainModel
    fun mapMovieListToEntity(domainModel: DomainModel) : Entity
}