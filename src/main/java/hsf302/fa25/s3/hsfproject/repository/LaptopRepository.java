package hsf302.fa25.s3.hsfproject.repository;

import hsf302.fa25.s3.hsfproject.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    Laptop findByLaptopCode(String laptopCode);
}
