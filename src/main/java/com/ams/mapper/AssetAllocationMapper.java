package com.ams.mapper;

import com.ams.dto.AllocationRespDto;
import com.ams.model.AssetAllocation;
import org.springframework.stereotype.Component;

@Component
public class AssetAllocationMapper {

    public AllocationRespDto entityToDto(AssetAllocation allocation) {
        return new AllocationRespDto(
                allocation.getId(),
                allocation.getEmployee().getId(),
                allocation.getEmployee().getUsername(),
                allocation.getAsset().getId(),
                allocation.getAsset().getAssetName(),
                allocation.getAsset().getAssetModel(),
                allocation.getAllocatedAt(),
                allocation.isReturned(),
                allocation.getReturnedAt()
        );
    }
}
