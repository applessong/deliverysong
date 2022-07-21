package shop.applesong.deliverysong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.applesong.deliverysong.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByName(Restaurant restaurant);

}
