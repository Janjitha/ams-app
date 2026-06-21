package com.ams.mapper;

import com.ams.dto.AssetRequestDto;
import com.ams.dto.AssetRequestRespDto;
import com.ams.enums.RequestStatus;
import com.ams.model.AssetRequest;
import org.springframework.stereotype.Component;

@Component
public class AssetRequestMapper {

    public AssetRequest dtoToEntity(AssetRequestDto dto) {
        AssetRequest assetRequest = new AssetRequest();
        assetRequest.setReason(dto.reason());
        assetRequest.setRequestStatus(RequestStatus.PENDING);
        return assetRequest;
    }

    public AssetRequestRespDto entityToDto(AssetRequest assetRequest) {
        return new AssetRequestRespDto(
                assetRequest.getId(),
                assetRequest.getReason(),
                assetRequest.getRequestStatus(),
                assetRequest.getEmployee().getId(),
                assetRequest.getEmployee().getUsername(),
                assetRequest.getAsset().getId(),
                assetRequest.getAsset().getAssetName(),
                assetRequest.getCreatedAt()
        );
    }
}
