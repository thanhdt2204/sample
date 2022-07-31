package com.example.sample.controller;

import com.example.sample.service.LoginService;
import com.example.sample.service.model.FormLogin;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @ApiOperation("API-LOGIN")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody FormLogin formLogin) {
        return ResponseEntity.ok().body(loginService.login(formLogin));
    }

}
