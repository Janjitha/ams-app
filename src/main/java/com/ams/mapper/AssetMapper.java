package com.ams.mapper;

import com.ams.dto.AssetDto;
import com.ams.dto.AssetRespDto;
import com.ams.model.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public Asset dtoToEntity(AssetDto dto) {
        Asset asset = new Asset();
        asset.setAssetName(dto.assetName());
        asset.setAssetModel(dto.assetModel());
        asset.setSerialNumber(dto.serialNumber());
        asset.setAssetStatus(dto.assetStatus());
        asset.setManufacturingDate(dto.manufacturingDate());
        asset.setExpiryDate(dto.expiryDate());
        asset.setAssetValue(dto.assetValue());
        return asset;
    }

    public AssetRespDto entityToDto(Asset asset) {
        return new AssetRespDto(
                asset.getId(),
                asset.getAssetName(),
                asset.getAssetModel(),
                asset.getSerialNumber(),
                asset.getAssetStatus(),
                asset.getCategory().getId(),
                asset.getCategory().getCategoryName(),
                asset.getManufacturingDate(),
                asset.getExpiryDate(),
                asset.getAssetValue(),
                asset.getImagePath()   // include imagePath in response
        );
    }
}
