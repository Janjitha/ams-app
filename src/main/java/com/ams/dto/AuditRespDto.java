package com.ams.dto;

import com.ams.enums.AuditStatus;
import java.time.Instant;

public record AuditRespDto(
        int id,
        int assetId,
        String assetName,
        String assetModel,
        int employeeId,
        String employeeUsername,
        AuditStatus status,
        Instant sentAt,
        Instant verifiedAt
) {
}
