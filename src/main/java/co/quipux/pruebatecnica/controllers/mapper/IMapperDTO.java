package co.quipux.pruebatecnica.controllers.mapper;

public interface IMapperDTO <D, T> {
    T toDTO(D domain);
    D toDomain(T t);
}
