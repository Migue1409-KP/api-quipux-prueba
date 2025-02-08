package co.quipux.pruebatecnica.controllers.mapper.concrete;

import co.quipux.pruebatecnica.controllers.mapper.IMapperDTO;
import co.quipux.pruebatecnica.controllers.support.TrackListDTO;
import co.quipux.pruebatecnica.domain.TrackListDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ISongMapperDTO.class)
public interface ITrackListMapperDTO extends IMapperDTO<TrackListDomain, TrackListDTO> {

    ITrackListMapperDTO INSTANCE = Mappers.getMapper(ITrackListMapperDTO.class);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "canciones", target = "songs")
    TrackListDomain toDomain(TrackListDTO dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "songs", target = "canciones")
    TrackListDTO toDTO(TrackListDomain domain);
}
