package com.coffeeshop.service;

import com.coffeeshop.dto.CoffeeMenuDTO;
import com.coffeeshop.dto.OrderResponse;
import com.coffeeshop.model.CoffeeMenu;
import com.coffeeshop.model.Order;
import com.coffeeshop.model.OrderItem;
import com.coffeeshop.model.Queue;
import com.coffeeshop.model.Shop;
import com.coffeeshop.model.User;
import com.coffeeshop.repository.CoffeeMenuRepository;
import com.coffeeshop.repository.QueueRepository;
import com.coffeeshop.repository.UserRepository;
import com.coffeeshop.repository.LoyaltyScoreRepository;
import com.coffeeshop.repository.OrderItemRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProcessOrderService {
    private final OrderRepository orderRepository;
    private final LoyaltyScoreRepository loyaltyScoreRepository;
    private final OrderItemRepository orderItemRepository;
    private final CoffeeMenuRepository coffeeMenuRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final QueueRepository queueRepository;

    public OrderResponse placeOrder(List<CoffeeMenuDTO> menuItems, Long shopId, Long customerId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        User customer = userRepository.findById(customerId).orElseThrow();
        Long availableQueueId = orderRepository.findQueueWithFewestInProgressOrders(shop.getId());
        if (availableQueueId==null)
            throw new EntityNotFoundException("No queue available");
        Queue availableQueue = queueRepository.findById(availableQueueId).orElseThrow();
        List<OrderItem> items = new ArrayList<>();
        menuItems.forEach(item -> {
            CoffeeMenu menuItem = coffeeMenuRepository.findById(item.getOrderItemId()).orElseThrow();
            OrderItem orderItem = OrderItem.builder()
                    .quantity(item.getQuantity())
                    .menuItem(menuItem)
                    .build();
            items.add(orderItem);
        });
        Order order = new Order();
        order.setOrderItems(items);
        order.setShop(shop);
        order.setCustomer(customer);
        order.setStatus("IN_PROGRESS");
        order.setQueue(availableQueue);
        order.setOrderTime(String.valueOf(LocalTime.now()));
        orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setQueueId(order.getQueue().getId());
        Long positionInQueue = orderRepository.findPositionInQueue(order.getQueue().getId(), order.getId());
        response.setPositionInQueue(positionInQueue);
        response.setWaitingTime(5 * positionInQueue);
        response.setShopId(shopId);
        return response;
    };
}
