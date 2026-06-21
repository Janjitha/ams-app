package com.ams.mapper;

import com.ams.dto.CategoryDto;
import com.ams.model.AssetCategory;
import org.springframework.stereotype.Component;

@Component
public class AssetCategoryMapper {

    public AssetCategory dtoToEntity(CategoryDto dto) {
        AssetCategory category = new AssetCategory();
        category.setCategoryName(dto.categoryName());
        category.setDescription(dto.description());
        return category;
    }
}
