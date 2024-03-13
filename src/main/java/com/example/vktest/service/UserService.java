package com.example.vktest.service;

import com.example.vktest.dto.response.UserResponse;
import com.example.vktest.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserResponse userResponse);

    void delete(Long id);

    Optional<User> get(Long id);

    List<User> getAll();
}
