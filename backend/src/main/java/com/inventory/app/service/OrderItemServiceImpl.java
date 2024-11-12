package com.inventory.app.service;
import com.inventory.app.dto.OrderItemDto;
import com.inventory.app.entity.OrderItem;
import com.inventory.app.repository.OrderItemRepository;
import com.inventory.app.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem orderItem) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
        if (existingOrderItem.isPresent()) {
            orderItem.setId(id);
            return orderItemRepository.save(orderItem);
        }
        return null; // Handle this appropriately in a real-world app
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    public OrderItemDto convertToDto(OrderItem item) {
        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setId(item.getId());
        itemDto.setProductName(item.getProduct().getName());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }

}
