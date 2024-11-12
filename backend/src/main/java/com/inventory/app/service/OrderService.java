package com.inventory.app.service;
import com.inventory.app.dto.OrderDto;
import com.inventory.app.entity.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);

    OrderDto convertToDto(Order order);

}
