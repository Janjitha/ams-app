package com.ams.service;

import com.ams.dto.AdminDashboardDto;
import com.ams.dto.EmployeeDashboardDto;
import com.ams.enums.AssetStatus;
import com.ams.enums.RequestStatus;
import com.ams.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardService {

    private final UserService userService;
    private final AssetService assetService;
    private final AssetRequestService assetRequestService;
    private final AssetAllocationService assetAllocationService;
    private final ServiceRequestService serviceRequestService;

    public AdminDashboardDto getAdminStats() {
        long totalUsers          = userService.countAll();
        long totalAssets         = assetService.countAll();
        long availableAssets     = assetService.countByStatus(AssetStatus.AVAILABLE);
        long allocatedAssets     = assetService.countByStatus(AssetStatus.ALLOCATED);
        long assetsUnderService  = assetService.countByStatus(AssetStatus.UNDER_SERVICE);
        long totalRequests       = assetRequestService.countAll();
        long pendingRequests     = assetRequestService.countByStatus(RequestStatus.PENDING);
        long totalServiceReqs    = serviceRequestService.countAll();
        long openServiceReqs     = serviceRequestService.countByStatus(ServiceStatus.OPEN);

        return new AdminDashboardDto(
                totalUsers,
                totalAssets,
                availableAssets,
                allocatedAssets,
                assetsUnderService,
                totalRequests,
                pendingRequests,
                totalServiceReqs,
                openServiceReqs
        );
    }

    public EmployeeDashboardDto getEmployeeStats(String username) {
        long myAllocatedAssets       = assetAllocationService.countMyActiveAllocations(username);
        long myPendingRequests       = assetRequestService.countMyRequestsByStatus(username, RequestStatus.PENDING);
        long myOpenServiceRequests   = serviceRequestService.countMyRequestsByStatus(username, ServiceStatus.OPEN);
        long myResolvedServiceReqs   = serviceRequestService.countMyRequestsByStatus(username, ServiceStatus.RESOLVED);

        return new EmployeeDashboardDto(
                myAllocatedAssets,
                myPendingRequests,
                myOpenServiceRequests,
                myResolvedServiceReqs
        );
    }
}
