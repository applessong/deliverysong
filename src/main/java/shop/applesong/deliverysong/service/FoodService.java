package shop.applesong.deliverysong.service;

import lombok.RequiredArgsConstructor;
import shop.applesong.deliverysong.DTO.FoodRequestDto;
import shop.applesong.deliverysong.model.Food;
import shop.applesong.deliverysong.model.Restaurant;
import shop.applesong.deliverysong.repository.FoodRepository;
import shop.applesong.deliverysong.repository.RestaurantRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository FoodRepository;
    private final RestaurantRepository RestaurantRepository;

    //특정 음식점에서 음식을 등록 . (특정 음식점 pk , 음식 리스트들을 받아온다.)
    @Transactional
    public void registerFood(Long id, List<FoodRequestDto> foodRequestDtoList) {

        //음식점을 찾아온다.
        Optional<Restaurant> findByRestaurantId = RestaurantRepository.findById(id);
        //Optional을 .get()-> 꺼낸다.
        Restaurant restaurant = findByRestaurantId.orElseThrow(
                () -> new NullPointerException("찾으시는 음식점이 없습니다.")
        );


        for(FoodRequestDto foodDto : foodRequestDtoList){

            String foodName = foodDto.getName();
            int foodPrice =foodDto.getPrice();

            Optional<Food> findFoodName = FoodRepository.findFoodByRestaurantAndName(restaurant,foodName);
            if(findFoodName.isPresent()){
                throw new IllegalArgumentException("이미 동일한 메뉴가 존재합니다.");
            }

            if(foodPrice < 100) throw  new IllegalArgumentException("최소 가격은 100원 이상입니다.");
            if(foodPrice > 1000000) throw  new IllegalArgumentException("최대 가격은 100만원입니다.");

            if(foodPrice % 100 > 0) throw new IllegalArgumentException("100원 단위로 입력해주세요");

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            //lIST의 값을 DTO 객체로 받아와 한개씩 저장 ,
            FoodRepository.save(food);
        }

    }

    public List<Food> foodListRestaurant(Long restaurantId) {

        Optional<Restaurant> restaurant = RestaurantRepository.findById(restaurantId);

        return FoodRepository.findFoodsByRestaurant(restaurant.orElseThrow(
                () -> new IllegalArgumentException("찾으시는 음식을 판매하는 음식점이 없습니다")
        ));

    }
}
