package cuahang.kimkhi_be.repository;

import cuahang.kimkhi_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IProductRepo extends PagingAndSortingRepository<Product, Long> {
    //tên không trùng lặp
    boolean existsByNameProduct(String nameProduct);

    Page<Product> findByNameProductContaining(String nameProduct, Pageable pageable);

    @Query(value = "select products.id as id,products.date_add as dateAdd,products.description as description,products.discount as discount," +
            "products.image as image,products.price as price,products.name_product as nameProduct,products.price as priceproducts,quantity as quantity," +
            "products.category_id as category" +
            "from products,category" +
            "where products.category_id=category.id" +
            "group by products.id limit 5;", nativeQuery = true)
    List<Product> getAllProduct(int index);
}
