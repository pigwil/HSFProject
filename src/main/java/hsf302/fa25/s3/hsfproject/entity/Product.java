package hsf302.fa25.s3.hsfproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_code", length = 50, nullable = false, unique = true)
    private String productCode;
    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;
    @Column(name = "product_status", length = 50, nullable = false)
    private String productStatus;
    @Column(name = "brand", length = 50, nullable = false)
    private String brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
