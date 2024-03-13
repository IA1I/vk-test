package com.example.vktest.service;

import com.example.vktest.dto.response.PostResponse;
import com.example.vktest.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(PostResponse postResponse);

    void delete(Long id);

    Optional<Post> get(Long id);

    List<Post> getAll();
}
