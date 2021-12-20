package cuahang.kimkhi_be.service.impl;

import cuahang.kimkhi_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public Page<Product> findAll(Pageable pageable);
    public Page<Product> findByNameProductContaining(String nameProduct,Pageable pageable);

    public Product addProduct(Product product);

    public Product updateProduct(Product product);

    public Optional<Product> getProductById(Long id);

    public Iterable<Product> getProductByAll();

    public void deleteProduct(Long id);

    List<Product> getAllProduct(int index);
//    public ArrayList<Product> findAllByTitle(String name);
}
