package com.security.demo.repository;

import com.security.demo.model.User;
import com.security.demo.model.UserLoginRequest;
import com.security.demo.model.UserRegistrationRequest;
import com.security.demo.model.UserUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepository {

    private static Set<User> users = new HashSet<>();


    public User register(UserRegistrationRequest u) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        User user = User
                .builder()
                .username(u.getUsername())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .password(u.getPassword())
                .id(users.size() + 1)
                .roles(roles)
                .build();
        users.add(user);
        return user;
    }

    public User login(UserLoginRequest u) {
        User user = users
                .stream()
                .filter(x ->
                    x.getUsername().equals(u.getUsername()) &&
                    x.getPassword().equals(u.getPassword())
                )
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("Authentication error");
        }
        return user;
    }

    public User getByUsername(String username) {
        return users
                .stream()
                .filter(x -> x.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User update(UserUpdateRequest u, String username) {
        User user = users
                .stream()
                .filter(x->x.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return null;
        }

        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());

        return user;
    }


}
