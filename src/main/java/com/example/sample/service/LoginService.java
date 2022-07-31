package com.example.sample.service;

import com.example.sample.security.TokenProvider;
import com.example.sample.service.model.FormLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public String login(FormLogin formLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formLogin.getEmail(), formLogin.getPassword())
        );
        return tokenProvider.createToken(formLogin.getEmail());
    }

}
