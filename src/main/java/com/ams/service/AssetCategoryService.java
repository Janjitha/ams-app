package com.ams.service;

import com.ams.dto.CategoryDto;
import com.ams.exception.ResourceNotFoundException;
import com.ams.mapper.AssetCategoryMapper;
import com.ams.model.AssetCategory;
import com.ams.repository.AssetCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetCategoryService {

    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetCategoryMapper assetCategoryMapper;

    public List<AssetCategory> getAll() {
        return assetCategoryRepository.findAll();
    }

    public AssetCategory addCategory(CategoryDto dto) {
        AssetCategory category = assetCategoryMapper.dtoToEntity(dto);
        return assetCategoryRepository.save(category);
    }

    public AssetCategory getById(int id) {
        return assetCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid category id"));
    }

    public AssetCategory updateCategory(int id, CategoryDto dto) {
        AssetCategory existingCategory = getById(id);
        existingCategory.setCategoryName(dto.categoryName());
        existingCategory.setDescription(dto.description());
        return assetCategoryRepository.save(existingCategory);
    }

    public void deleteCategory(int id) {
        getById(id); // validation
        assetCategoryRepository.deleteById(id);
    }
}
