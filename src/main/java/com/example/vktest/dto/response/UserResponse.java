package com.example.vktest.dto.response;

public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        String phone,
        String website
) {
}
