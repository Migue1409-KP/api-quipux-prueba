package co.quipux.pruebatecnica.repository;

import co.quipux.pruebatecnica.data.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISongRepository extends JpaRepository<SongEntity, UUID> {

}
