package com.ams.dto;

import com.ams.enums.AssetStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record AssetDto(
        @NotBlank(message = "This field is mandatory")
        @NotNull(message = "This field is mandatory")
        String assetName,
        String assetModel,
        String serialNumber,
        @NotNull(message = "This field is mandatory")
        AssetStatus assetStatus,
        Instant manufacturingDate,
        Instant expiryDate,
        BigDecimal assetValue
) {
}
