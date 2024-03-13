package com.example.vktest.client;

import com.example.vktest.configuration.ApplicationConfiguration;
import com.example.vktest.dto.request.UserRequest;
import com.example.vktest.dto.response.UserResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class UserClient {
    private static final String CONTENT_TYPE = "Content-type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CHARSET_UTF_8 = "charset=UTF-8";
    private final WebClient webClient;


    public UserClient(ApplicationConfiguration configuration) {
        this(configuration.baseUrl());
    }

    public UserClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<UserResponse[]> getAllUsers() {
        return webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToMono(UserResponse[].class);
    }

    public Mono<UserResponse> getUserById(long id) {
        return webClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    public Mono<UserResponse> createUser(com.example.vktest.dto.request.UserRequest requestBody) {
        return webClient
                .post()
                .uri("/users")
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    public Mono<UserResponse> updateUser(long id, UserRequest requestBody) {
        return webClient
                .put()
                .uri("/users/{id}", id)
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    public Mono<Void> deleteUser(long id) {
        return webClient
                .delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
