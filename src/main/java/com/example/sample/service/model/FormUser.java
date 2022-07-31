package com.example.sample.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormUser {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

}
