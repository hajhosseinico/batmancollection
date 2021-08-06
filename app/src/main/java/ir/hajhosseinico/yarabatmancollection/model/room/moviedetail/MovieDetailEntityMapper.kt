package ir.hajhosseinico.yarabatmancollection.model.room.moviedetail

/**
 * Movie detail entity mapper interface
 */
interface MovieDetailEntityMapper<Entity, DomainModel> {
    fun mapMovieDetailFromEntity(domainModel: Entity) : DomainModel
    fun mapMovieDetailToEntity(domainModel: DomainModel) : Entity
}