package shop.applesong.deliverysong.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodOrderDto {
    String name;
    int quantity;
    int price;
}
