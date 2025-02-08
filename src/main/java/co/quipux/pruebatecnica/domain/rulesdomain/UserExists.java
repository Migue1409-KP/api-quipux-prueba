package co.quipux.pruebatecnica.domain.rulesdomain;

import co.quipux.pruebatecnica.domain.exceptions.UserDoesNotExist;
import co.quipux.pruebatecnica.repository.IUserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserExists {
    private final IUserRepository userRepository;

    public UserExists(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validationRuleEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserDoesNotExist("El email del usuario ya existe.");
        }
    }
}
