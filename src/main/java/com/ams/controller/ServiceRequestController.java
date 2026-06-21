package com.ams.controller;

import com.ams.dto.ServiceRequestDto;
import com.ams.dto.ServiceRequestRespDto;
import com.ams.enums.ServiceStatus;
import com.ams.service.ServiceRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/service-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    // Employee raises service request — uses Principal
    @PostMapping("/add/{assetId}")
    public ServiceRequestRespDto addServiceRequest(@Valid @RequestBody ServiceRequestDto dto,
                                                   @PathVariable int assetId,
                                                   Principal principal) {
        return serviceRequestService.addServiceRequest(dto, principal.getName(), assetId);
    }

    // Employee views their own service requests
    @GetMapping("/my")
    public List<ServiceRequestRespDto> getMyServiceRequests(Principal principal) {
        return serviceRequestService.getMyServiceRequests(principal.getName());
    }

    // Admin views all service requests
    @GetMapping("/all")
    public List<ServiceRequestRespDto> getAll() {
        return serviceRequestService.getAll();
    }

    // Admin updates status
    @PutMapping("/{id}/update-status")
    public ServiceRequestRespDto updateStatus(@PathVariable int id,
                                              @RequestParam ServiceStatus serviceStatus) {
        return serviceRequestService.updateStatus(id, serviceStatus);
    }
}