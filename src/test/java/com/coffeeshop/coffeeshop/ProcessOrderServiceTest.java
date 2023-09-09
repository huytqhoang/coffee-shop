package com.coffeeshop.coffeeshop;

import com.coffeeshop.dto.CoffeeMenuDTO;
import com.coffeeshop.model.*;
import com.coffeeshop.repository.*;
import com.coffeeshop.service.ProcessOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProcessOrderServiceTest {

    @InjectMocks
    private ProcessOrderService processOrderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private LoyaltyScoreRepository loyaltyScoreRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CoffeeMenuRepository coffeeMenuRepository;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QueueRepository queueRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void placeOrder_ShouldCreateOrderSuccessfully() {
        // Mock data
        Long shopId = 1L;
        Long customerId = 2L;
        Shop mockShop = new Shop();
        mockShop.setId(shopId);// Create a Shop instance with required data
        User mockCustomer = new User(); // Create a User instance with required data
        CoffeeMenuDTO mockMenuItemDTO = new CoffeeMenuDTO(); // Create a mock CoffeeMenuDTO instance
        List<CoffeeMenuDTO> menuItems = new ArrayList<>();
        menuItems.add(mockMenuItemDTO);

        // Mock repository behavior
        when(shopRepository.findById(shopId)).thenReturn(Optional.of(mockShop));
        when(userRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
        when(orderRepository.findQueueWithFewestInProgressOrders(any())).thenReturn(1L); // Mock the queue
        when(queueRepository.findById(mockShop.getId())).thenReturn(Optional.of(new Queue())); // Mock the queue
        when(coffeeMenuRepository.findById(mockMenuItemDTO.getOrderItemId())).thenReturn(Optional.of(new CoffeeMenu())); // Mock CoffeeMenu

        // Execute the method
        processOrderService.placeOrder(menuItems, shopId, customerId);


        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
