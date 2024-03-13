package com.example.vktest.dto.request;

public record PostRequest(
        Long userId,
        String title,
        String body
) {
}
