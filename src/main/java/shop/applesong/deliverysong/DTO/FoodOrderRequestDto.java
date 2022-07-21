package shop.applesong.deliverysong.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodOrderRequestDto extends OrderResponseDto {
    private Long restaurantId;
    private int quantity;
    private List<FoodOrderRequestDto> foods;
    private int price;
    private int totalPrice;
    private int deliveryFee;

}
