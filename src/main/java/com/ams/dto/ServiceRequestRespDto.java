package com.ams.dto;

import com.ams.enums.ServiceStatus;

import java.time.Instant;

public record ServiceRequestRespDto(
        int serviceRequestId,
        String issueDescription,
        String issueType,
        ServiceStatus serviceStatus,
        int employeeId,
        String employeeUsername,
        int assetId,
        String assetName,
        Instant createdAt,
        Instant updatedAt
) {
}
