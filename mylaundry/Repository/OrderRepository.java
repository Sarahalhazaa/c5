package com.example.mylaundry.Repository;

import com.example.mylaundry.Model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);

    @Query("select a from Order a where a.status='Waiting'")
    List<Order> findOrders();

//    @Modifying
//    @Transactional
//    @Query("UPDATE Order p SET p.status='Shipment has arrived at warehouse' WHERE p.id= :orderId")
 //   void updateOrderStatusById(Integer orderId);

    List<Order> findOrdersByCustomerId(Integer customerId);

    List<Order> findOrdersByDriverId(Integer deliverId);
}