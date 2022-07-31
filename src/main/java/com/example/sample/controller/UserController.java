package com.example.sample.controller;

import com.example.sample.domain.User;
import com.example.sample.pagination.CustomPage;
import com.example.sample.pagination.PaginationUtil;
import com.example.sample.service.UserService;
import com.example.sample.service.model.FormUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("API-GET-ALL-USERS")
    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CustomPage<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok().body(PaginationUtil.customPage(userService.findAll(pageable)));
    }

    @ApiOperation("API-CREATE-USER")
    @PostMapping("/user")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> createUser(@Valid @RequestBody FormUser formUser) {
        return ResponseEntity.ok().body(userService.create(formUser));
    }

    @ApiOperation("API-UPDATE-USER")
    @PutMapping("/user")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> updateUser(@Valid @RequestBody FormUser formUser) {
        return ResponseEntity.ok().body(userService.update(formUser));
    }

    @ApiOperation("API-DELETE-USER")
    @DeleteMapping("/user/{email}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.delete(email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("API-GET-USER")
    @GetMapping("/user/{email}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.get(email));
    }

}
