package shop.applesong.deliverysong.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    String name;
    int quantity;
    int price;

    public OrderResponseDto() {
        this.name=getName();
        this.quantity=getQuantity();
        this.price=getPrice();
    }
}
