package co.quipux.pruebatecnica.data.mapper;

public interface IMapperEntity<E, D> {
    D toDomain(E entity);
    E toEntity(D domain);
}
