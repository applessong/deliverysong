package shop.applesong.deliverysong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.applesong.deliverysong.model.OrderList;
import shop.applesong.deliverysong.model.Orders;

import java.util.List;

public interface OrderListRepository  extends JpaRepository<OrderList, Long> {
    List<OrderList> findAllByOrder(Orders order);
}
