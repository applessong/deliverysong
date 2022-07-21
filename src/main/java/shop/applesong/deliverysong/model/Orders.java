package shop.applesong.deliverysong.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oder_id;

//    @ManyToOne
    @JoinColumn(name="retaurantId")
    private String restaurantName;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @Column(name = "total_price")
    private int totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="orders_id")
    private List<OrderList> OrderLists;

    @Column
    private int deliveryFee;

    public Orders(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public void totalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

