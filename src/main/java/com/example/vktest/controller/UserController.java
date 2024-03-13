package com.example.vktest.controller;

import com.example.vktest.client.UserClient;
import com.example.vktest.dto.request.UserRequest;
import com.example.vktest.dto.response.UserResponse;
import com.example.vktest.entity.User;
import com.example.vktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserClient userClient;
    private final UserService userService;

    @Autowired
    public UserController(UserClient userClient, UserService userService) {
        this.userClient = userClient;
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public ResponseEntity<UserResponse[]> getAllUsers() {
        UserResponse[] userResponses = userClient.getAllUsers().block();
        List<User> users = userService.getAll();

        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse userResponse = userClient.getUserById(id).block();
        Optional<User> user = userService.get(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest requestBody) {
        UserResponse userResponse = userClient.createUser(requestBody).block();
        userService.save(userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest requestBody) {
        UserResponse userResponse = userClient.updateUser(id, requestBody).block();
        userService.save(userResponse);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userClient.deleteUser(id).block();
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
