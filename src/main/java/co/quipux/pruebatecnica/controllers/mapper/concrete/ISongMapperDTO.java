package co.quipux.pruebatecnica.controllers.mapper.concrete;

import co.quipux.pruebatecnica.controllers.mapper.IMapperDTO;
import co.quipux.pruebatecnica.controllers.support.SongDTO;
import co.quipux.pruebatecnica.domain.SongDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISongMapperDTO extends IMapperDTO<SongDomain, SongDTO> {

    ISongMapperDTO INSTANCE = Mappers.getMapper(ISongMapperDTO.class);

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "artista", target = "artist")
    @Mapping(source = "anno", target = "year")
    @Mapping(source = "genero", target = "genre")
    SongDomain toDomain(SongDTO dto);

    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "artist", target = "artista")
    @Mapping(source = "year", target = "anno")
    @Mapping(source = "genre", target = "genero")
    SongDTO toDTO(SongDomain domain);
}

