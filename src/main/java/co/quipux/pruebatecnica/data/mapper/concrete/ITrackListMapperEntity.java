package co.quipux.pruebatecnica.data.mapper.concrete;

import co.quipux.pruebatecnica.data.TrackListEntity;
import co.quipux.pruebatecnica.data.mapper.IMapperEntity;
import co.quipux.pruebatecnica.domain.TrackListDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ITrackListMapperEntity extends IMapperEntity<TrackListEntity, TrackListDomain> {

    ITrackListMapperEntity INSTANCE = Mappers.getMapper(ITrackListMapperEntity.class);

    @Mapping(source = "songs", target = "songs")
    TrackListDomain toDomain(TrackListEntity entity);

    @Mapping(source = "songs", target = "songs")
    TrackListEntity toEntity(TrackListDomain domain);
}
