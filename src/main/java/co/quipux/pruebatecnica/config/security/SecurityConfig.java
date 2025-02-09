package co.quipux.pruebatecnica.config.security;

import co.quipux.pruebatecnica.config.security.filters.JwtFilterAuthentication;
import co.quipux.pruebatecnica.config.security.service.CustomerDetailUserService;
import co.quipux.pruebatecnica.util.JwtUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtility jwtUtility;
    private final CustomerDetailUserService customerDetailUserService;

    public SecurityConfig(JwtUtility jwtUtility, CustomerDetailUserService customerDetailUserService) {
        this.jwtUtility = jwtUtility;
        this.customerDetailUserService = customerDetailUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/users/login").permitAll()
                                .requestMatchers("/api/v1/users/register").permitAll()
                                .requestMatchers("/api/v1/lists/dummy").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilterAuthentication(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtFilterAuthentication jwtFilterAuthentication() {
        return new JwtFilterAuthentication(jwtUtility, customerDetailUserService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
