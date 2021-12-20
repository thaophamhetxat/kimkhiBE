package cuahang.kimkhi_be.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int quantity;   //số lượng
    int unitPrice;   //đơn giá
    int intoMoney;   //thành tiền

    @ManyToOne
    Product product;
}
