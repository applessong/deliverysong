package shop.applesong.deliverysong.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.applesong.deliverysong.DTO.OrderRequestDto;
import shop.applesong.deliverysong.DTO.OrderResponseDto;
import shop.applesong.deliverysong.service.OrderService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/order/request")
    public OrderResponseDto orderRequest(@RequestBody OrderRequestDto OrderRequestDto) {

        //음식점 번호 받아오고 , 음식 번호 / 수량 받아온다.
        return orderService.orderFood(OrderRequestDto);


    }

    @GetMapping("/orders")
    public List<OrderResponseDto> checkOrders() {

        return orderService.oderList();
    }
}