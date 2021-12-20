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
public class BillOfSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String nameBuyer; // tên khách hàng
    Date saleDate;   //ngày mua
    int totalMoney;  // tổng tiền
    boolean status;  //trạng thái

    @ManyToOne
    SaleDetail saleDetail;
}
