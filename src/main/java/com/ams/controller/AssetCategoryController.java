package com.ams.controller;

import com.ams.dto.CategoryDto;
import com.ams.model.AssetCategory;
import com.ams.service.AssetCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class AssetCategoryController {

    private final AssetCategoryService assetCategoryService;

    @PostMapping("/add")
    public AssetCategory addCategory(@Valid @RequestBody CategoryDto dto) {
        return assetCategoryService.addCategory(dto);
    }

    @GetMapping("/all")
    public List<AssetCategory> getAll() {
        return assetCategoryService.getAll();
    }

    @PutMapping("/update/{id}")
    public AssetCategory updateCategory(@PathVariable int id,
                                        @Valid @RequestBody CategoryDto dto) {
        return assetCategoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id) {
        assetCategoryService.deleteCategory(id);
    }
}
