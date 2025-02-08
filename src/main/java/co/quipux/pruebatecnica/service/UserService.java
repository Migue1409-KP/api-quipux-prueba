package co.quipux.pruebatecnica.service;

import co.quipux.pruebatecnica.data.mapper.concrete.IUserMapperEntity;
import co.quipux.pruebatecnica.domain.UserDomain;
import co.quipux.pruebatecnica.domain.rulesdomain.impl.CreateUserDomain;
import co.quipux.pruebatecnica.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IUserMapperEntity userMapperEntity;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserDomain createUserDomain;

    public UserService(IUserRepository userRepository, IUserMapperEntity userMapperEntity, PasswordEncoder passwordEncoder, CreateUserDomain createUserDomain) {
        this.userRepository = userRepository;
        this.userMapperEntity = userMapperEntity;
        this.passwordEncoder = passwordEncoder;
        this.createUserDomain = createUserDomain;
    }

    public UserDomain createUser(UserDomain user) {
        createUserDomain.validationRulesDomain(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapperEntity.toDomain(userRepository.save(userMapperEntity.toEntity(user)));
    }
}
