package com.example.vktest.client;

import com.example.vktest.configuration.ApplicationConfiguration;
import com.example.vktest.dto.request.AlbumRequest;
import com.example.vktest.dto.response.AlbumResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AlbumClient {
    private static final String CONTENT_TYPE = "Content-type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CHARSET_UTF_8 = "charset=UTF-8";
    private final WebClient webClient;


    public AlbumClient(ApplicationConfiguration configuration) {
        this(configuration.baseUrl());
    }

    public AlbumClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<AlbumResponse[]> getAllAlbums() {
        return webClient
                .get()
                .uri("/albums")
                .retrieve()
                .bodyToMono(AlbumResponse[].class);
    }

    public Mono<AlbumResponse> getAlbumById(long id) {
        return webClient
                .get()
                .uri("/albums/{id}", id)
                .retrieve()
                .bodyToMono(AlbumResponse.class);
    }

    public Mono<AlbumResponse> createAlbum(com.example.vktest.dto.request.AlbumRequest requestBody) {
        return webClient
                .post()
                .uri("/albums")
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(AlbumResponse.class);
    }

    public Mono<AlbumResponse> updateAlbum(long id, AlbumRequest requestBody) {
        return webClient
                .put()
                .uri("/albums/{id}", id)
                .bodyValue(requestBody)
                .header(CONTENT_TYPE, APPLICATION_JSON, CHARSET_UTF_8)
                .retrieve()
                .bodyToMono(AlbumResponse.class);
    }

    public Mono<Void> deleteAlbum(long id) {
        return webClient
                .delete()
                .uri("/albums/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
