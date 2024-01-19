package com.example.orders.controller;

import com.example.orders.model.Orders;
import com.example.orders.model.dto.OrdersReadRes2;
import com.example.orders.model.dto.OrdersUpdateReq;
import com.example.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(Orders orders){
        orderService.create(orders);

        return ResponseEntity.ok().body("예약에 성공하였습니다");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(orderService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(Long id){
        return ResponseEntity.ok().body(orderService.read(id));
    }



    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<OrdersReadRes2> update(@RequestBody OrdersUpdateReq ordersUpdateReq) {
        //ResponseEntity 반환, orderDto를 매개변수로 받아옴
        OrdersReadRes2 result = orderService.update(ordersUpdateReq);
        //orderService의 업데이트 메소드에 orderDto를 받아옴

        return ResponseEntity.ok().body(result);
    }



    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<OrdersReadRes2> delete(Long idx){
        orderService.delete(idx);

        OrdersReadRes2 result2 = orderService.delete(idx);

        return ResponseEntity.ok().body(result2);
    }





}
