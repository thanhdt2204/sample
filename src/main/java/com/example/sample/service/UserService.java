package com.example.sample.service;

import com.example.sample.config.Constants;
import com.example.sample.domain.User;
import com.example.sample.exception.BadRequestException;
import com.example.sample.repository.UserRepository;
import com.example.sample.service.model.FormUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAllByRole(Constants.USER, pageable);
    }

    public User create(FormUser formUser) {
        // Check email
        Optional<User> existUser = userRepository.findOneByEmail(formUser.getEmail());
        if (existUser.isPresent()) {
            throw new BadRequestException("Email exist");
        }

        User user = new User();
        user.setEmail(formUser.getEmail());
        user.setFirstName(formUser.getFirstName());
        user.setLastName(formUser.getLastName());
        user.setRole(Constants.USER);
        user.setPassword(passwordEncoder.encode(Constants.DEFAULT_PASSWORD));

        return userRepository.save(user);
    }

    public User update(FormUser formUser) {
        // Get exist user
        User existUser = userRepository.findOneByEmail(formUser.getEmail()).orElseThrow(
                () -> new BadRequestException("User not found")
        );

        existUser.setFirstName(formUser.getFirstName());
        existUser.setLastName(formUser.getLastName());

        return userRepository.save(existUser);
    }

    public void delete(String email) {
        userRepository.findOneByEmailAndRole(email, Constants.USER).ifPresent(userRepository::delete);
    }

    public User get(String email) {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new BadRequestException("User not found"));
    }

}
