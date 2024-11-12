package com.inventory.app.service;
import com.inventory.app.dto.OrderDto;
import com.inventory.app.entity.Order;
import com.inventory.app.repository.OrderRepository;
import com.inventory.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;



    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null; // Handle this appropriately in a real-world app
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }


    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());

        // Convert each OrderItem to OrderItemDto
        orderDto.setItems(order.getItems().stream()
                .map(orderItemService::convertToDto)
                .collect(Collectors.toList()));

        return orderDto;
    }

}
