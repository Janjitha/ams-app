package com.ams.dto;

public record AdminDashboardDto(
        long totalUsers,
        long totalAssets,
        long availableAssets,
        long allocatedAssets,
        long assetsUnderService,
        long totalRequests,
        long pendingRequests,
        long totalServiceRequests,
        long openServiceRequests
) {
}
