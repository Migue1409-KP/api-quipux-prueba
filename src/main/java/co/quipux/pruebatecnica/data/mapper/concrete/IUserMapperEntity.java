package co.quipux.pruebatecnica.data.mapper.concrete;

import co.quipux.pruebatecnica.data.UserEntity;
import co.quipux.pruebatecnica.data.mapper.IMapperEntity;
import co.quipux.pruebatecnica.domain.UserDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapperEntity extends IMapperEntity<UserEntity, UserDomain> {

    UserEntity toEntity(UserDomain userDomain);
    UserDomain toDomain(UserEntity entity);
}
