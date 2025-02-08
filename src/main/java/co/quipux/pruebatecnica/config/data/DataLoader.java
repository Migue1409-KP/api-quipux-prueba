package co.quipux.pruebatecnica.config.data;

import co.quipux.pruebatecnica.data.UserEntity;
import co.quipux.pruebatecnica.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "example@example.com";
            UserEntity existingUser = userRepository.findByEmail(email);

            if (ObjectUtils.isEmpty(existingUser)) {
                UserEntity user = new UserEntity();
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode("Example321!"));
                user.setRole("PRUEBA");

                userRepository.save(user);
            }
        };
    }
}
