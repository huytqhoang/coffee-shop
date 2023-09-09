package com.coffeeshop.repository;

import com.coffeeshop.model.Order;
import com.coffeeshop.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "SELECT q.id FROM queue q " +
            "WHERE q.shop_id = :shopId " +
            "AND (SELECT COUNT(*) FROM \"order\" o WHERE o.status = 'IN_PROGRESS' AND o.shop_id = :shopId AND o.queue_id = q.id) < q.max_size " +
            "ORDER BY (SELECT COUNT(*) FROM \"order\" o WHERE o.status = 'IN_PROGRESS' AND o.shop_id = :shopId AND o.queue_id = q.id) ASC " +
            "LIMIT 1", nativeQuery = true)
    Long findQueueWithFewestInProgressOrders(@Param("shopId") Long shopId);

    @Query(value = "WITH RankedOrders AS (" +
            "    SELECT o.queue_id AS queue_id, o.customer_id AS customer_id, o.id AS order_id, " +
            "           ROW_NUMBER() OVER (PARTITION BY o.queue_id ORDER BY o.id) AS position_in_queue " +
            "    FROM \"order\" o " +
            "    WHERE o.status = 'IN_PROGRESS' AND o.queue_id = :target_queue_id" +
            ")" +
            "SELECT r.position_in_queue " +
            "FROM RankedOrders r " +
            "WHERE r.order_id = :target_order_id", nativeQuery = true)
    Long findPositionInQueue(@Param("target_queue_id") Long targetQueueId, @Param("target_order_id") Long targetOrderId);
}


