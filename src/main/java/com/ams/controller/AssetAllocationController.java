package com.ams.controller;

import com.ams.dto.AllocationRespDto;
import com.ams.service.AssetAllocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/asset-allocations")
@CrossOrigin(origins = "http://localhost:5173")
public class AssetAllocationController {

    private final AssetAllocationService assetAllocationService;

    // Admin views all allocations
    @GetMapping("/all")
    public List<AllocationRespDto> getAll() {
        return assetAllocationService.getAll();
    }

    // Employee views their own allocations — uses Principal
    @GetMapping("/my")
    public List<AllocationRespDto> getMyAllocations(Principal principal) {
        return assetAllocationService.getMyAllocations(principal.getName());
    }

    // Employee returns an asset
    @PutMapping("/{id}/return")
    public AllocationRespDto returnAsset(@PathVariable int id) {
        return assetAllocationService.returnAsset(id);
    }
}

