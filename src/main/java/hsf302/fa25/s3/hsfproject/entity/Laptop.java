package hsf302.fa25.s3.hsfproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "laptops")
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "laptop_code", length = 50, nullable = false, unique = true)
    private String laptopCode;
    @Column(name = "laptop_name", length = 100, nullable = false)
    private String laptopName;
    @Column(name = "laptop_status", length = 50, nullable = false)
    private String laptopStatus;
    @Column(name = "brand", length = 50, nullable = false)
    private String brand;
    @Column(name = "CPU_Info", length = 255)
    private String cpuInfo;
    @Column(name = "RAM_Info", length = 255)
    private String ramInfo;

    @ManyToOne
    @JoinColumn(
            name = "user_code",
            referencedColumnName = "user_code",
            nullable = false
    )
    private User user;


    @OneToMany(mappedBy = "laptop", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
