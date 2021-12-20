package cuahang.kimkhi_be.repository;

import cuahang.kimkhi_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IProductRepo extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findByNameProductContaining(String nameProduct, Pageable pageable);

    @Query(value = "select product.id as id,product.nameProduct as nameProduct, product.image as image,product.quantity as quantity, " +
            "product.importPrice as importPrice, product.price as price, product.discount as discount, " +
            "product.description as description, product.dateAdd as dateAdd, product.category as category " +
            "from product "+
            "where product.delete_flag = 0 " +
            "group by vaccine.id limit ?1,3;", nativeQuery = true)
    List<Product> getAllProduct(int index);
}
