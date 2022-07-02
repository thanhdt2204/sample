package com.example.sample.service;

import com.example.sample.security.TokenProvider;
import com.example.sample.service.model.FormLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TokenProvider tokenProvider;

    public String login(FormLogin formLogin) {
        return tokenProvider.createToken(formLogin.getUsername());
    }

}
