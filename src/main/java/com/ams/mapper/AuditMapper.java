package com.ams.mapper;

import com.ams.dto.AuditRespDto;
import com.ams.model.AssetAudit;
import org.springframework.stereotype.Component;

@Component
public class AuditMapper {

    public AuditRespDto entityToDto(AssetAudit audit) {
        return new AuditRespDto(
                audit.getId(),
                audit.getAsset().getId(),
                audit.getAsset().getAssetName(),
                audit.getAsset().getAssetModel(),
                audit.getEmployee().getId(),
                audit.getEmployee().getUsername(),
                audit.getStatus(),
                audit.getSentAt(),
                audit.getVerifiedAt()
        );
    }
}
