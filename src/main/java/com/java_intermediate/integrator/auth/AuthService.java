package com.java_intermediate.integrator.auth;

import com.java_intermediate.integrator.jwt.JwtService;
import com.java_intermediate.integrator.user.Role;
import com.java_intermediate.integrator.user.User;
import com.java_intermediate.integrator.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user =  userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken();
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(LoginRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken())
                .build();
    }
}
