package com.example.mylaundry.Repository;

import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders findOrderById(Integer id);

    @Query("select a from Orders a where a.status='Waiting'")
    List<Orders> findOrders();

//    @Modifying
//    @Transactional
//    @Query("UPDATE Order p SET p.status='Shipment has arrived at warehouse' WHERE p.id= :orderId")
 //   void updateOrderStatusById(Integer orderId);

    List<Orders> findOrdersByCustomerId(Integer customerId);

    List<Orders> findOrdersByDriverId(Integer deliverId);
}