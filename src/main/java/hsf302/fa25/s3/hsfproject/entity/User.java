package hsf302.fa25.s3.hsfproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "username", unique = true, length = 20, nullable = false)
    private String username;
    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;
    @Column(name = "password", length = 64, nullable = false)
    private String password;
    @Column(name = "role", length = 20, nullable = false)
    private String role;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
