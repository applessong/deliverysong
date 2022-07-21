package shop.applesong.deliverysong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.applesong.deliverysong.DTO.FoodRequestDto;
import shop.applesong.deliverysong.model.Food;
import shop.applesong.deliverysong.service.FoodService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService FoodService;


    @GetMapping("/restaurants/{restaurantId}/foods")
    public List<Food> foodList(@PathVariable Long restaurantId){
        return FoodService.foodListRestaurant(restaurantId);
    }


    @PostMapping("/restaurants/{restaurantId}/foods/register")
    public void registerFoodList(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDtoList){

        FoodService.registerFood(restaurantId,foodRequestDtoList);
    }

}

