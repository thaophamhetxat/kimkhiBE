package cuahang.kimkhi_be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String nameProduct;  //tên sản phẩm
    String image;
    int quantity;   //số lượng
    int importPrice;     //giá nhập
    int price;  // giá bán
    int discount;   //chiết khấu
    String description;       //mô tả
    Date dateAdd;  // ngày nhập

    @ManyToOne
    Category category;
}
