package co.quipux.pruebatecnica.domain.rulesdomain;

import co.quipux.pruebatecnica.domain.exceptions.TrackListDoesNotExist;
import co.quipux.pruebatecnica.repository.ITrackListRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrackListExists {
    private final ITrackListRepository trackListRepository;

    public TrackListExists(ITrackListRepository trackListRepository) {
        this.trackListRepository = trackListRepository;
    }

    public void validationRuleId(UUID id) {
        if (!trackListRepository.existsById(id)) {
            throw new TrackListDoesNotExist("El id de la lista de reproducción no existe.");
        }
    }

    public void validationRuleNameDoesNotExist(String name) {
        if (!trackListRepository.existsByName(name)) {
            throw new TrackListDoesNotExist("El nombre de la lista de reproducción no existe.");
        }
    }

    public void validationRuleNameExist(String name) {
        if (trackListRepository.existsByName(name)) {
            throw new TrackListDoesNotExist("El nombre de la lista de reproducción ya existe.");
        }
    }

    public void validationRuleNameNotNull(String name) {
        if (name == null) {
            throw new TrackListDoesNotExist("El nombre de la lista de reproducción no puede ser nulo.");
        }
    }

    public void validationRuleNameNotSpecialSymbols(String name) {
        if (name.matches(".*[!@#$%^&*()+{}|:<>?].*")) {
            throw new TrackListDoesNotExist("El nombre de la lista de reproducción no puede contener caracteres especiales.");
        }
    }
}
