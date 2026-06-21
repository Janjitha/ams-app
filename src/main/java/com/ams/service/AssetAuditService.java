package com.ams.service;

import com.ams.dto.AuditRespDto;
import com.ams.enums.AuditStatus;
import com.ams.mapper.AuditMapper;
import com.ams.model.Asset;
import com.ams.model.AssetAllocation;
import com.ams.model.AssetAudit;
import com.ams.model.User;
import com.ams.repository.AssetAllocationRepository;
import com.ams.repository.AssetAuditRepository;
import com.ams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetAuditService {

    private final AssetAuditRepository auditRepository;
    private final AssetAllocationRepository allocationRepository;
    private final UserRepository userRepository;
    private final AuditMapper auditMapper;

    // Admin will be sending audit request to every employee who has been allocated
    public List<AuditRespDto> sendAuditToAll() {
        List<AssetAllocation> activeAllocations = allocationRepository.findByReturnedFalse();

        List<AssetAudit> audits = activeAllocations.stream()
                .map(allocation -> {
                    AssetAudit audit = new AssetAudit();
                    audit.setAsset(allocation.getAsset());
                    audit.setEmployee(allocation.getEmployee());
                    audit.setStatus(AuditStatus.PENDING);
                    return audit;
                })
                .toList();

        List<AssetAudit> saved = auditRepository.saveAll(audits);
        return saved.stream().map(auditMapper::entityToDto).toList();
    }

    // Admin - get all audits
    public List<AuditRespDto> getAllAudits() {
        return auditRepository.findAll()
                .stream()
                .map(auditMapper::entityToDto)
                .toList();
    }

    // Employee -get my pending audits
    public List<AuditRespDto> getMyAudits(String username) {
        User employee = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return auditRepository.findByEmployee(employee)
                .stream()
                .map(auditMapper::entityToDto)
                .toList();
    }

    // Employee - verify audit
    public AuditRespDto verifyAudit(int id) {
        AssetAudit audit = findById(id);
        audit.setStatus(AuditStatus.VERIFIED);
        audit.setVerifiedAt(Instant.now());
        return auditMapper.entityToDto(auditRepository.save(audit));
    }

    // Employee- reject audit
    public AuditRespDto rejectAudit(int id) {
        AssetAudit audit = findById(id);
        audit.setStatus(AuditStatus.REJECTED);
        audit.setVerifiedAt(Instant.now());
        return auditMapper.entityToDto(auditRepository.save(audit));
    }

    private AssetAudit findById(int id) {
        return auditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Audit record not found with id: " + id));
    }
}
