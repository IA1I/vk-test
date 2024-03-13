package com.example.vktest.controller;

import com.example.vktest.client.PostsClient;
import com.example.vktest.dto.request.PostRequest;
import com.example.vktest.dto.response.PostResponse;
import com.example.vktest.entity.Post;
import com.example.vktest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsClient postsClient;
    private final PostService postService;

    @Autowired
    public PostsController(PostsClient postsClient, PostService postService) {
        this.postsClient = postsClient;
        this.postService = postService;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    public ResponseEntity<PostResponse[]> getAllPosts() {
        PostResponse[] postResponses = postsClient.getAllPosts().block();
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") Long id) {
        PostResponse postResponse = postsClient.getPostById(id).block();
        Optional<Post> post = postService.get(id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest requestBody) {
        PostResponse postResponse = postsClient.createPost(requestBody).block();
        Post post = postService.save(postResponse);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("id") Long id, @RequestBody PostResponse requestBody) {
        PostResponse postResponse = postsClient.updatePost(id, requestBody).block();
        Post post = postService.save(postResponse);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postsClient.deletePost(id).block();
        postService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
