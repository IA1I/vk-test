package com.example.vktest.client;

import com.example.vktest.configuration.ApplicationConfiguration;
import com.example.vktest.dto.response.PostResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PostsClient {
    private static final String CONTENT_TYPE = "Content-type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CHARSET_UTF_8 = "charset=UTF-8";
    private final WebClient webClient;


    public PostsClient(ApplicationConfiguration configuration) {
        this(configuration.baseUrl());
    }

    public PostsClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<PostResponse[]> getAllPosts() {
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(PostResponse[].class);
    }

    public Mono<PostResponse> getPostById(long id) {
        return webClient
                .get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostResponse.class);
    }

    public Mono<PostResponse> createPost(com.example.vktest.dto.request.PostRequest requestBody) {
        return webClient
                .post()
                .uri("/posts")
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(PostResponse.class);
    }

    public Mono<PostResponse> updatePost(long id, PostResponse requestBody) {
        return webClient
                .put()
                .uri("/posts/{id}", id)
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(PostResponse.class);
    }

    public Mono<Void> deletePost(long id) {
        return webClient
                .delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
