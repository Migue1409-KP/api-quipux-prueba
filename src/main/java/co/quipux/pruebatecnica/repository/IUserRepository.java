package co.quipux.pruebatecnica.repository;

import co.quipux.pruebatecnica.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
