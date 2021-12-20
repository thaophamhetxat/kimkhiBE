package cuahang.kimkhi_be.service;

import cuahang.kimkhi_be.model.Product;
import cuahang.kimkhi_be.repository.IProductRepo;
import cuahang.kimkhi_be.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepo iProductRepo;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepo.findAll(pageable);
    }

    @Override
    public Page<Product> findByNameProductContaining(String nameProduct, Pageable pageable) {
        return iProductRepo.findByNameProductContaining(nameProduct,pageable);
    }

    @Override
    public Product addProduct(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return iProductRepo.findById(id);
    }

    @Override
    public Iterable<Product> getProductByAll() {
        return iProductRepo.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRepo.findById(id);
    }

    @Override
    public List<Product> getAllProduct(int index) {
        return iProductRepo.getAllProduct(index);
    }
}
