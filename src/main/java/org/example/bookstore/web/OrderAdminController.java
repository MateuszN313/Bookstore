package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.dto.OrderStatusRequest;
import org.example.bookstore.models.Order;
import org.example.bookstore.services.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@AllArgsConstructor
public class OrderAdminController {
    private final IOrderService orderService;

    @GetMapping
    public List<Order> list(){
        return this.orderService.findAllOrders();
    }

    @PostMapping("/status")
    public Order changeStatus(@RequestBody OrderStatusRequest orderStatusRequest){
        return this.orderService.changeStatus(orderStatusRequest.orderId(), orderStatusRequest.statusName());
    }
}
