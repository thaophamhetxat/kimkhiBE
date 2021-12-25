package cuahang.kimkhi_be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity

//tạo bảng có tên không trùng lặp
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameProduct"
        })
})
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

    @ManyToOne
    Users users;

    public Product() {
    }

    public Product(long id, String nameProduct, String image, int quantity, int importPrice,
                   int price, int discount, String description, Date dateAdd, Category category, Users users) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.image = image;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.dateAdd = dateAdd;
        this.category = category;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
