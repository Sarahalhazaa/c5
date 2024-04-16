package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Driver;
import com.example.mylaundry.Model.Order;
import com.example.mylaundry.Repository.DriverRepository;
import com.example.mylaundry.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;


    public List<Driver> getDriver() {
        return driverRepository.findAll();
    }

    public void addDriver(Driver driver) {

        driverRepository.save(driver);
    }

    public void updateDriver(Integer id, Driver driver) {
        Driver driver1 = driverRepository.findDriversById(id);
        if (driver1 == null) {
            throw new ApiException("id not found");
        }
       driver1.setEmail(driver.getEmail());
        driver1.setPassword(driver.getPassword());
        driver1.setPhoneNumber(driver.getPhoneNumber());
        driverRepository.save(driver1);
    }

    public void deleteDriver(Integer id) {
        Driver driver = driverRepository.findDriversById(id);
        if (driver == null) {
            throw new ApiException("id not found");
        }
        driverRepository.delete(driver);
    }

    //---------------------------  end CRUD  ---------------------------------

    //-----------------------   1 endPoint   ------------------------------

    public Integer getEvaluationOfDriver( Integer driverId) {
        Driver driver = driverRepository.findDriversById(driverId);
        if (driver == null) {
            throw new ApiException("id not found");
        }
        return  driver.getEvaluation();
    }

    //-----------------------   2 endPoint   ------------------------------

    public List<Order> findOrders() {
        List<Order> orders = orderRepository.findOrders();
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return  orders;
    }

    //-----------------------   3 endPoint   ------------------------------

    public void delivery(Integer driverId ,Integer orderId ) {
        Order order1= orderRepository.findOrderById(orderId);
        if(order1==null) {
            throw new ApiException("order id not found");
        }
        Driver driver1 = driverRepository.findDriversById(driverId);
        if (driver1 == null) {
            throw new ApiException("driver id not found");
        }
        if (order1.getStatus().equalsIgnoreCase("accepted")){
            order1.setDriverId(driverId);
            orderRepository.save(order1);
            driver1.setDeliveryOrders(driver1.getDeliveryOrders()+1);
            driverRepository.save(driver1);

        }
    }

    //-----------------------   4 endPoint   ------------------------------

    public List<Order> previousOrders(Integer driverId) {
        ArrayList<Order> orders2 = new ArrayList<>();
        Driver drivers = driverRepository.findDriversById(driverId);
        if (drivers == null) {
            throw new ApiException("driver id not found");
        }
        List<Order> orders3 = orderRepository.findOrdersByDriverId(driverId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    //-----------------------   5 endPoint   ------------------------------

    public List<Order> currentOrders(Integer driverId) {
        ArrayList<Order> orders2 = new ArrayList<>();
        Driver driver = driverRepository.findDriversById(driverId);
        if (driver == null) {
            throw new ApiException("driver id not found");
        }
        List<Order> orders3 = orderRepository.findOrdersByDriverId(driverId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    //-----------------------   6 endPoint   ------------------------------

    public void updateStatus(Integer driverId ,Integer orderId ) {
        Order order1 = orderRepository.findOrderById(orderId);
        if (order1 == null) {
            throw new ApiException("order id not found");
        }
        Driver driver1 = driverRepository.findDriversById(driverId);
        if (driver1 == null) {
            throw new ApiException("driver id not found");
        }

        if (order1.getDriverId() == driverId) {
            if (order1.getStatus().equalsIgnoreCase("accepted")) {
                order1.setStatus("received stuff");
                orderRepository.save(order1);
            } else if (order1.getStatus().equalsIgnoreCase("received to laundry")) {
                order1.setStatus("Delivered");
                orderRepository.save(order1);

            }
        }

    }




}