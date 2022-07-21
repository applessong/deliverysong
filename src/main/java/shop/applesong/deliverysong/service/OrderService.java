package shop.applesong.deliverysong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.applesong.deliverysong.DTO.FoodOrderDto;
import shop.applesong.deliverysong.DTO.FoodOrderRequestDto;
import shop.applesong.deliverysong.DTO.OrderRequestDto;
import shop.applesong.deliverysong.DTO.OrderResponseDto;
import shop.applesong.deliverysong.model.Food;
import shop.applesong.deliverysong.model.OrderList;
import shop.applesong.deliverysong.model.Orders;
import shop.applesong.deliverysong.model.Restaurant;
import shop.applesong.deliverysong.repository.FoodRepository;
import shop.applesong.deliverysong.repository.OrderListRepository;
import shop.applesong.deliverysong.repository.OrdersRepository;
import shop.applesong.deliverysong.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderListRepository orderListRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;


    public OrderResponseDto orderFood(OrderRequestDto orderRequestDto) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Orders orders = new Orders(restaurant);
        Orders order = ordersRepository.save(orders);
        int totalPrice = 0;

        // OrderList
        List<OrderList> orderLists = new ArrayList<>();

        for (int i = 0; i < orderRequestDto.getFoods().size(); i++) {
            Long foodId = orderRequestDto.getFoods().get(i).getRestaurantId();
            Food food = foodRepository.findById(foodId).get();
            int quantity = orderRequestDto.getFoods().get(i).getQuantity();
            int price = food.getPrice() * quantity;

            if(quantity>100 || quantity < 1)
                throw new IllegalArgumentException("허용 수량 초과");

            orderLists.add(new OrderList(order, food, quantity, price));

            totalPrice += price;
        }
        int minOrderPrice = Math.toIntExact(restaurant.getMinOrderPrice());

        if (totalPrice < minOrderPrice) throw new IllegalArgumentException("최소 주문금액은"+minOrderPrice+"입니다.");

        List<OrderList> orderList = orderListRepository.saveAll(orderLists);
        // orderList

        int deliveryFee = Math.toIntExact(restaurant.getDeliveryFee());

        int totalTotalPrice = totalPrice + deliveryFee;

        order.totalPrice(totalTotalPrice);

        //List 는 add 로 담음.
        orderListRepository.saveAll(orderList);

        order.totalPrice(totalPrice);

        return new OrderResponseDto();
    }

    public List<OrderResponseDto> oderList() {
        List<OrderResponseDto> orderDtoList = new ArrayList<>();

        //요청된 Orders 의 전체목록을 불러오자.
        List<Orders> ordersList = ordersRepository.findAll();
        System.out.println("주문 전체목록 : "+ ordersList.size());
        //Orders에는 음식점 정보, 음식점 이름,  주문목록 정보, 배달료, 총가격이있다.

        //전체목록을 반복을 통해 하나하나 분리해보자.
        for(Orders orders : ordersList){
            //OrderDto에 들어갈 List를 만들어준다.
            List<FoodOrderRequestDto> foodOrderList = new ArrayList<>();
            //name quantity price



            //주문서의 주문목록들을 가져온다.
            //주문목록에는 음식이름, 가격 ,수량 ,가격, 음식정보, 주문정보
            List<OrderList> orderItemList = orders.getOrderLists();
            //OrderItems가 없음.

            for(OrderList orderLists: orderItemList){
                System.out.println(orderLists);
                FoodOrderRequestDto foodOrderDto = FoodOrderRequestDto.builder()
                        .restaurantId(Long.valueOf(orderLists.getName()))
                        .quantity(orderLists.getQuantity())
                        .price(orderLists.getPrice())
                        .build();

                foodOrderList.add(foodOrderDto);
            }

            FoodOrderRequestDto orderDto = FoodOrderRequestDto.builder()
                    .restaurantId(orders.getRestaurant().getRestaurantId())
                    .totalPrice(orders.getTotalPrice())
                    .deliveryFee(orders.getDeliveryFee())
                    .foods(foodOrderList)
                    .build();

            orderDtoList.add(orderDto);


        }
        return orderDtoList;

    }
}
