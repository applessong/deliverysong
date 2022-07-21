package shop.applesong.deliverysong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.applesong.deliverysong.DTO.RestaurantRequestDto;
import shop.applesong.deliverysong.model.Restaurant;
import shop.applesong.deliverysong.repository.RestaurantRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository RestaurantRepository;
//    private final RestaurantService RestaurantService;

    //음식점 등록 메서드
    public Restaurant registerRestaurant(RestaurantRequestDto restaurantRequestDto){
        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        long deliveryFee = restaurantRequestDto.getDeliveryFee();
        long minOrderPrice = restaurantRequestDto.getMinOderPrice();
        if(!(minOrderPrice >= 1000 && minOrderPrice <= 100000)){
            throw new IllegalStateException("금액 설정 범위 초과");

        }
        if(minOrderPrice % 100 > 0){
            throw new IllegalStateException("금액 단위 오류");
        }


        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalStateException("금액 설정 범위 초과");

        }

        if(deliveryFee % 500 > 0){
            throw new IllegalStateException("금액 단위 오류");
        }




        RestaurantRepository.save(restaurant);
        return restaurant;
    }


    //음식점 목록 조회 메서드 //레스토랑 리포를 인용해서 모든 컬럼을 불러온 후 참조변수list에 담음
    public List<Restaurant> getRestaurantList(){
        List<Restaurant> restaurants = RestaurantRepository.findAll();
        return restaurants;
    }



}
