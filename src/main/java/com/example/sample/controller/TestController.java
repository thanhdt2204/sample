package com.example.sample.controller;

import com.example.sample.service.TestService;
import com.example.sample.service.model.FormLogin;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @ApiOperation("API-LOGIN")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody FormLogin formLogin) {
        return ResponseEntity.ok().body(testService.login(formLogin));
    }

    @ApiOperation("API-TEST-AUTHENTICATION")
    @GetMapping("/test")
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("Hello Spring Boot");
    }

}
