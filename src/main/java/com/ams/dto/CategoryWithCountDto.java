package com.ams.dto;

public record CategoryWithCountDto(
        int id,
        String categoryName,
        String description,
        int assetCount
) {}
