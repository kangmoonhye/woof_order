package com.example.orders.service;

import com.example.orders.model.Orders;
import com.example.orders.model.dto.*;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void create(Orders orders) {
        orderRepository.save(Orders.builder()
                .phoneNumber(orders.getPhoneNumber())
                .time(orders.getTime())
                .orderDetails(orders.getOrderDetails())
                .place(orders.getPlace())
                .reservation_status(orders.getReservation_status())
                .build());
    }

    public OrdersListRes list() {
        List<Orders> result = orderRepository.findAll();
        List<OrdersReadRes> orderDtos = new ArrayList<>();

        for (Orders orders:result) {

            List<OrdersReadRes> ordersReadRes = new ArrayList<>();

            OrdersReadRes ordersReadRes1 = OrdersReadRes.builder()
                    .idx(orders.getIdx())
                    .place(orders.getPlace())
                    .time(orders.getTime())
                    .phoneNumber(orders.getPhoneNumber())
                    .reservation_status(orders.getReservation_status())
                    .build();

            orderDtos.add(ordersReadRes1);
        }
        return OrdersListRes.builder()
                .code(1000)
                .message("조회 성공.")
                .success(true)
                .isSuccess(true)
                .result(orderDtos)
                .build();

    }

    public OrdersReadRes2 read(Long id) {
        Optional<Orders> result = orderRepository.findById(id);
        List<OrdersReadRes2> orderDto2 = new ArrayList<>();

        if (result.isPresent()) {
            Orders orders = result.get();


            OrdersReadRes ordersReadRes = OrdersReadRes.builder()
                    .idx(orders.getIdx())
                    .phoneNumber(orders.getPhoneNumber())
                    .time(orders.getTime())
                    .place(orders.getPlace())
                    .reservation_status(orders.getReservation_status())
                    .build();

            return OrdersReadRes2.builder()
                    .code(1000)
                    .message("요청 성공.")
                    .success(true)
                    .isSuccess(true)
                    .result(ordersReadRes)
                    .build();
        } else {
            return OrdersReadRes2.builder()
                    .code(400)
                    .message("요청 실패.")
                    .success(false)
                    .isSuccess(false)
                    .result(OrdersReadRes.builder()
                            .reservation_status("실패")
                            .build())
                    .build();
        }
    }

    public OrdersReadRes2 update(OrdersUpdateReq ordersUpdateReq) {
//        OrdersReadRes2를 반환하는 업데이트 메소드
        //OrderDto를 매개변수로 받아온다
        Optional<Orders> result = orderRepository.findById(ordersUpdateReq.getIdx());
        //오더 레포에서 id를 찾아 result에 저장한다

        if(result.isPresent()) {
            //만약 result에 값이 있다면
            Orders orders = result.get();
            //orders에 result를 저장한다

//            orders.setStatus(orderDto.getStatus());
            //orders의 status는 orderDto의 status를 찾아 가져온다

            Orders orders1 = Orders.builder()
                    .idx(ordersUpdateReq.getIdx())
                    .time(ordersUpdateReq.getTime())
                    .build();

           orderRepository.save(orders1);
            //order레포에 orders를 저장한다

            OrdersReadRes2 response = OrdersReadRes2.builder()
                    .code(1000)
                    .message("요청 성공.")
                    .success(true)
                    .isSuccess(true)
                    .result(OrdersReadRes.builder()
                            .reservation_status("주문 수정에 성공하였습니다.")
                            .build())
                    .build();
            //주문 요청 성공시 "주문 수정 성공"을 반환한다

            return response;

        }else {
            return OrdersReadRes2.builder()
                    .code(400)
                    .message("요청 실패.")
                    .success(false)
                    .isSuccess(false)
                    .result(OrdersReadRes.builder()
                            .reservation_status("주문 수정에 실패하였습니다.주문 ID가 유효하지 않습니다.")
                            .build())
                    .build();
        }//주문 실패시 "주문 실패"를 반환한다

    }

    @Transactional
    public OrdersReadRes2 delete(Long idx) {
        orderRepository.delete(
                Orders.builder()
                        .idx(idx)
                        .build());

        OrdersReadRes2 response2 = OrdersReadRes2.builder()
                .code(200)
                .message("요청 성공.")
                .success(true)
                .isSuccess(true)
                .result(OrdersReadRes.builder()
                        .reservation_status("주문 삭제 성공헀습니다")
                        .build())
                .build();

        return response2;
    }

}
