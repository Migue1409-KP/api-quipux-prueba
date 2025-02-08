package co.quipux.pruebatecnica.controllers;

import co.quipux.pruebatecnica.config.security.service.CustomUserDetails;
import co.quipux.pruebatecnica.controllers.support.Response;
import co.quipux.pruebatecnica.domain.UserDomain;
import co.quipux.pruebatecnica.service.UserService;
import co.quipux.pruebatecnica.util.JwtUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(JwtUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response<String>> createAuthenticationToken(@RequestBody UserDomain userDomain) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDomain.getEmail(), userDomain.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        String jwt = jwtUtility.generateToken(userDetails,userDetails.getId(),userDetails.getRole());

        return ResponseEntity.ok(new Response<>(HttpStatus.OK, "User logged", jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Response<UserDomain>> registerUser(@RequestBody UserDomain userDomain) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK, "User registered", userService.createUser(userDomain)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        }
    }
}
