package hsf302.fa25.s3.hsfproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(
            name = "created_by",
            referencedColumnName = "user_code",
            nullable = false
    )
    private User createdBy;

    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "order_type", length = 20, nullable = false)
    private String orderType;
    @Column(name = "order_status", length = 20, nullable = false)
    private String orderStatus;
    @Column(name = "attributes", length = 200)
    private String attributes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
