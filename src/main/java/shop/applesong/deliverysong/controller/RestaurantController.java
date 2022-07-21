package shop.applesong.deliverysong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.applesong.deliverysong.DTO.RestaurantRequestDto;
import shop.applesong.deliverysong.model.Restaurant;
import shop.applesong.deliverysong.repository.RestaurantRepository;
import shop.applesong.deliverysong.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@RequiredArgsConstructor.AnyAnnotation
@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository RestaurantRepository;
    private final RestaurantService RestaurantService;


    //음식점 리스트 조회 맵핑 //서비스클래스의 getRestaurantList메서드 호출
    @GetMapping("/restaurants/list")
    public List<Restaurant> restaurantList(){
        return RestaurantService.getRestaurantList();
    }

    @PostMapping("/restaurants/register")
    public Restaurant register(@RequestBody RestaurantRequestDto requestDto){
       return RestaurantService.registerRestaurant(requestDto);
    }

}
