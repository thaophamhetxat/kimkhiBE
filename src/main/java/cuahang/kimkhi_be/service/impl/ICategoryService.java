package cuahang.kimkhi_be.service.impl;

import cuahang.kimkhi_be.model.Category;

import java.util.Optional;

public interface ICategoryService {
    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Optional<Category> getCategoryById(Long id);

    public Iterable<Category> getCategoryByAll();

    public void deleteCategory(Long id);
}
