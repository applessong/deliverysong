package shop.applesong.deliverysong.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.applesong.deliverysong.DTO.RestaurantRequestDto;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_list")
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long restaurantId;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private Long minOrderPrice;

    @Column(nullable = false)
    private Long deliveryFee;


    public Restaurant(RestaurantRequestDto requestDto){
        this.restaurantName = requestDto.getRestaurantName();
        this.minOrderPrice = requestDto.getMinOderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}
