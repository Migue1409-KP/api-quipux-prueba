package co.quipux.pruebatecnica.repository;

import co.quipux.pruebatecnica.data.TrackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITrackListRepository extends JpaRepository<TrackListEntity, UUID> {
    TrackListEntity findByName(String name);
    boolean existsByName(String name);
}
