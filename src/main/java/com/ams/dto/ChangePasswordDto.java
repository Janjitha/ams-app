package com.ams.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(
        @NotBlank
        String username,
        @NotBlank
        String newPassword
) {
}