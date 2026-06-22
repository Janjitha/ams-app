package com.ams.dto;

public record CategoryDetailDto(
        int id,
        String categoryName,
        String description,
        int totalAssets,
        String apiVersion
) {}
