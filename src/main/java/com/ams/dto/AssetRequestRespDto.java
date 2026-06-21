package com.ams.dto;

import com.ams.enums.RequestStatus;

import java.time.Instant;

public record AssetRequestRespDto(
        int requestId,
        String reason,
        RequestStatus requestStatus,
        int employeeId,
        String employeeUsername,
        int assetId,
        String assetName,
        Instant createdAt
) {
}
