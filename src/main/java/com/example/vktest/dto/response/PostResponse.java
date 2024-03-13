package com.example.vktest.dto.response;

public record PostResponse(
        Long userId,
        Long id,
        String title,
        String body
) {
}
