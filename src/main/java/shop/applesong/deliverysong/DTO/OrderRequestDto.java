package shop.applesong.deliverysong.DTO;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequestDto {

    private Long restaurantId;

    private List<FoodOrderRequestDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
