package com.security.demo.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private Integer id;
    private String username;
    private String password;


    private String firstName;
    private String lastName;
    private List<String> roles;
}
