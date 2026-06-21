package com.ams.dto;

public record EmployeeDashboardDto(
        long myAllocatedAssets,
        long myPendingRequests,
        long myOpenServiceRequests,
        long myResolvedServiceRequests
) {
}
