package co.quipux.pruebatecnica.data.mapper.concrete;

import co.quipux.pruebatecnica.data.SongEntity;
import co.quipux.pruebatecnica.data.mapper.IMapperEntity;
import co.quipux.pruebatecnica.domain.SongDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISongMapperEntity extends IMapperEntity<SongEntity, SongDomain> {
    SongDomain toDomain(SongEntity entity);
    SongEntity toEntity(SongDomain domain);
}
