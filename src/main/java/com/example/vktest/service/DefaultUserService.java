package com.example.vktest.service;

import com.example.vktest.dto.response.UserResponse;
import com.example.vktest.entity.User;
import com.example.vktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User save(UserResponse userResponse) {
        User user = mapUserResponseToUser(userResponse);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        var source = userRepository.findAll();
        List<User> target = new ArrayList<>();
        source.forEach(target::add);

        return target;
    }

    private User mapUserResponseToUser(UserResponse userResponse) {
        User user = new User();
        user.setId(userResponse.id());
        user.setName(userResponse.name());
        user.setUsername(userResponse.username());
        user.setEmail(userResponse.email());
        user.setPhone(userResponse.phone());
        user.setWebsite(userResponse.website());
        return user;
    }
}
