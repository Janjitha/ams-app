package com.ams.dto;

public record LoginResponseDto(
        String token,
        String username,
        String role
) {
}