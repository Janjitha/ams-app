package com.ams.controller;

import com.ams.dto.CategoryDetailDto;
import com.ams.dto.CategoryWithCountDto;
import com.ams.model.AssetCategory;
import com.ams.repository.AssetRepository;
import com.ams.service.AssetCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class AssetCategoryControllerV2 {

    private final AssetCategoryService assetCategoryService;
    private final AssetRepository assetRepository;

    // V2 API 1: Get category by ID with detailed info
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDetailDto> getCategoryById(@PathVariable int id) {
        AssetCategory category = assetCategoryService.getById(id);
        int totalAssets = assetRepository.getAssetsByCategoryId(id).size();
        CategoryDetailDto response = new CategoryDetailDto(
                category.getId(),
                category.getCategoryName(),
                category.getDescription(),
                totalAssets,
                "v2"
        );
        return ResponseEntity.ok(response);
    }

    // V2 API 2: Search categories by name (case-insensitive, partial match)
    @GetMapping("/search")
    public ResponseEntity<List<AssetCategory>> searchByName(@RequestParam String name) {
        List<AssetCategory> filtered = assetCategoryService.getAll().stream()
                .filter(c -> c.getCategoryName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return ResponseEntity.ok(filtered);
    }

    // V2 API 3: Get all categories with their asset counts
    @GetMapping("/all-with-count")
    public ResponseEntity<List<CategoryWithCountDto>> getAllWithAssetCount() {
        List<CategoryWithCountDto> result = assetCategoryService.getAll().stream()
                .map(cat -> new CategoryWithCountDto(
                        cat.getId(),
                        cat.getCategoryName(),
                        cat.getDescription(),
                        assetRepository.getAssetsByCategoryId(cat.getId()).size()
                ))
                .toList();
        return ResponseEntity.ok(result);
    }
}
