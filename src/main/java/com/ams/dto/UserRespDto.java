package com.ams.dto;

import com.ams.enums.Role;

import java.time.Instant;

public record UserRespDto(
        int id,
        String username,
        Role role,
        Instant createdAt
) {
}
