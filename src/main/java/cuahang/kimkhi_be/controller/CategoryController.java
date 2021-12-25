package cuahang.kimkhi_be.controller;


import cuahang.kimkhi_be.dto.response.ResponseMessage;
import cuahang.kimkhi_be.model.Category;
import cuahang.kimkhi_be.model.Product;
import cuahang.kimkhi_be.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kimkhi/categories/")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category) {
        return ResponseEntity.ok(iCategoryService.addCategory(category));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(iCategoryService.getCategoryByAll());
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return iCategoryService.getCategoryById(id).get();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        Optional productOptional = Optional.ofNullable(iCategoryService.getCategoryById(id));
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(iCategoryService.updateCategory(category), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        iCategoryService.deleteCategory(id);
    }
    
}
