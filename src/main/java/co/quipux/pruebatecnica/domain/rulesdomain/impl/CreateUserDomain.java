package co.quipux.pruebatecnica.domain.rulesdomain.impl;

import co.quipux.pruebatecnica.domain.rulesdomain.UserExists;
import org.springframework.stereotype.Service;

@Service
public class CreateUserDomain {
    private final UserExists userExists;

    public CreateUserDomain(UserExists userExists) {
        this.userExists = userExists;
    }

    public void validationRulesDomain(String email) {
        userExists.validationRuleEmailExist(email);
    }
}
