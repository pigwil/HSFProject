package hsf302.fa25.s3.hsfproject.repository;

import hsf302.fa25.s3.hsfproject.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
