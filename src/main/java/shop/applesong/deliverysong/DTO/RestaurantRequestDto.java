package shop.applesong.deliverysong.DTO;

import lombok.Getter;

@Getter
public class RestaurantRequestDto {
    private String restaurantName;
    private Long minOderPrice;
    private Long deliveryFee;
}
