package com.example.vktest.service;

import com.example.vktest.dto.response.PostResponse;
import com.example.vktest.entity.Post;
import com.example.vktest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultPostService implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public DefaultPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post save(PostResponse postResponse) {
        Post post = mapPostResponseToPost(postResponse);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Post> get(Long id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Post> getAll() {
        var source = postRepository.findAll();
        List<Post> target = new ArrayList<>();
        source.forEach(target::add);

        return target;
    }

    private Post mapPostResponseToPost(PostResponse postResponse) {
        Post post = new Post();
        post.setId(postResponse.id());
        post.setUserId(postResponse.userId());
        post.setTitle(postResponse.title());
        post.setBody(postResponse.body());
        return post;
    }
}
