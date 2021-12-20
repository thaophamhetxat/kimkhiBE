package cuahang.kimkhi_be.service;

import cuahang.kimkhi_be.model.Category;
import cuahang.kimkhi_be.repository.ICategoryRepo;
import cuahang.kimkhi_be.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;

    @Override
    public Category addCategory(Category category) {
        return iCategoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return iCategoryRepo.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return iCategoryRepo.findById(id);
    }

    @Override
    public Iterable<Category> getCategoryByAll() {
        return iCategoryRepo.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        this.iCategoryRepo.deleteById(id);
    }
}
