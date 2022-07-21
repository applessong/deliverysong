package shop.applesong.deliverysong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.applesong.deliverysong.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
