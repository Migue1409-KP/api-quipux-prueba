package co.quipux.pruebatecnica.config.security.service;

import co.quipux.pruebatecnica.repository.IUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerDetailUserService implements UserDetailsService {

    private final IUserRepository userRepository;

    public CustomerDetailUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userEntity = userRepository.findByEmail(email);


        return new CustomUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                true,true,true,true,
                Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole())),
                userEntity.getId(),userEntity.getRole()
        );
    }
}
