package co.quipux.pruebatecnica.config.security.filters;

import co.quipux.pruebatecnica.config.security.service.CustomerDetailUserService;
import co.quipux.pruebatecnica.util.JwtUtility;
import co.quipux.pruebatecnica.util.ObejctUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilterAuthentication extends OncePerRequestFilter {
    private final JwtUtility jwtUtility;
    private final CustomerDetailUserService customerDetailUserService;

    public JwtFilterAuthentication(JwtUtility jwtUtility, CustomerDetailUserService customerDetailUserService) {
        this.jwtUtility = jwtUtility;
        this.customerDetailUserService = customerDetailUserService;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            jwt = authorizationHeader.substring(7);
            username = jwtUtility.extractUserName(jwt);

        }

        if (!ObejctUtility.isNull(username) && ObejctUtility.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.customerDetailUserService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(jwtUtility.validateToken(jwt, userDetails))) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
