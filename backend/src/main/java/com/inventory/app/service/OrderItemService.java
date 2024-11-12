package com.inventory.app.service;
import com.inventory.app.dto.OrderItemDto;
import com.inventory.app.entity.OrderItem;
import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    OrderItem updateOrderItem(Long id, OrderItem orderItem);
    void deleteOrderItem(Long id);

    OrderItemDto convertToDto(OrderItem item);
}
