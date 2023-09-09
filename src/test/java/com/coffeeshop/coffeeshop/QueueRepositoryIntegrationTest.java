package com.coffeeshop.coffeeshop;

import com.coffeeshop.model.Order;
import com.coffeeshop.model.OrderItem;
import com.coffeeshop.model.Queue;
import com.coffeeshop.model.Shop;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.repository.QueueRepository;
import com.coffeeshop.repository.ShopRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class QueueRepositoryIntegrationTest {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DirtiesContext
    void testFindQueueWithFewestCustomers() {
        Shop shop = new Shop();
        shopRepository.save(shop);
        // Create and save queues with different customer counts
        Queue queueWithFewestCustomers = new Queue();
        queueWithFewestCustomers.setMaxSize(1); // Adjust the maximum queue size as needed
        queueWithFewestCustomers.setShop(shop); // Fewest customers
        queueRepository.save(queueWithFewestCustomers);

        Queue queueWithMoreCustomers = new Queue();
        queueWithMoreCustomers.setMaxSize(100); // Adjust the maximum queue size as needed
        queueWithMoreCustomers.setShop(shop);   // More customers
        queueRepository.save(queueWithMoreCustomers);

        Queue queueWithEvenMoreCustomers = new Queue();
        queueWithMoreCustomers.setMaxSize(1); // Adjust the maximum queue size as needed
        queueWithMoreCustomers.setShop(shop);   // More customers
        queueRepository.save(queueWithEvenMoreCustomers);

        Order order = new Order();
        order.setQueue(queueWithMoreCustomers);
        order.setShop(shop);
        order.setStatus("IN_PROGRESS");
        orderRepository.save(order);

        Order order2 = new Order();
        order.setQueue(queueWithEvenMoreCustomers);
        order.setShop(shop);
        order.setStatus("IN_PROGRESS");
        orderRepository.save(order2);
        // Invoke the findQueueWithFewestCustomers method
        long result = orderRepository.findQueueWithFewestInProgressOrders(shop.getId());

        // Assertions
        assertNotNull(result);
        assertEquals(queueWithFewestCustomers.getId(), result);
    }
}
