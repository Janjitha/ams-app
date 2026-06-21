package com.ams.service;

import com.ams.dto.ServiceRequestDto;
import com.ams.dto.ServiceRequestRespDto;
import com.ams.enums.AssetStatus;
import com.ams.enums.ServiceStatus;
import com.ams.exception.ResourceNotFoundException;
import com.ams.mapper.ServiceRequestMapper;
import com.ams.model.Asset;
import com.ams.model.ServiceRequest;
import com.ams.model.User;
import com.ams.repository.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final ServiceRequestMapper serviceRequestMapper;
    private final UserService userService;
    private final AssetService assetService;

    public ServiceRequestRespDto addServiceRequest(ServiceRequestDto dto, String employeeUsername, int assetId) {
        // Fetch employee from DB based on given username
        User employee = userService.getByUsername(employeeUsername);
        // Fetch asset from DB based on given assetId
        Asset asset = assetService.getById(assetId);
        // Map dto to entity
        ServiceRequest serviceRequest = serviceRequestMapper.dtoToEntity(dto);
        // Attach employee and asset
        serviceRequest.setEmployee(employee);
        serviceRequest.setAsset(asset);
        // Change asset status to UNDER_SERVICE
        asset.setAssetStatus(AssetStatus.UNDER_SERVICE);
        // Save
        ServiceRequest saved = serviceRequestRepository.save(serviceRequest);
        return serviceRequestMapper.entityToDto(saved);
    }

    public List<ServiceRequestRespDto> getAll() {
        return serviceRequestRepository.findAll()
                .stream()
                .map(serviceRequestMapper::entityToDto)
                .toList();
    }

    public List<ServiceRequestRespDto> getMyServiceRequests(String employeeUsername) {
        return serviceRequestRepository.findByEmployeeUsername(employeeUsername)
                .stream()
                .map(serviceRequestMapper::entityToDto)
                .toList();
    }

    public ServiceRequest getById(int id) {
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid service request id"));
    }

    public ServiceRequestRespDto updateStatus(int serviceRequestId, ServiceStatus serviceStatus) {
        ServiceRequest serviceRequest = getById(serviceRequestId);
        serviceRequest.setServiceStatus(serviceStatus);

        // If resolved, change asset status back to ALLOCATED
        if (serviceStatus == ServiceStatus.RESOLVED) {
            serviceRequest.getAsset().setAssetStatus(AssetStatus.ALLOCATED);
        }

        ServiceRequest saved = serviceRequestRepository.save(serviceRequest);
        return serviceRequestMapper.entityToDto(saved);
    }

    public long countAll() {
        return serviceRequestRepository.count();
    }

    public long countByStatus(ServiceStatus status) {
        return serviceRequestRepository.countByServiceStatus(status);
    }

    public long countMyRequestsByStatus(String username, ServiceStatus status) {
        return serviceRequestRepository.countByEmployeeUsernameAndServiceStatus(username, status);
    }
}
