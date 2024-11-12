package com.inventory.app.service;
import com.inventory.app.entity.Category;
import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category updatedCategory);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
}
