package shop.applesong.deliverysong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.applesong.deliverysong.model.Food;
import shop.applesong.deliverysong.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {

    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
    List<Food> findFoodsByRestaurant(Restaurant restaurant);

}
