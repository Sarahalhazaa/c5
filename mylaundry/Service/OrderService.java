package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Repository.CustomerRepository;
import com.example.mylaundry.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ServiceLaundryService serviceLaundryRepository;

    public List<Orders> getAllOrder(){
        return orderRepository.findAll();

    }

    public void addOrder(Orders order){
        orderRepository.save(order);
    }


//    public void addOrder(Integer CId , Integer SId){
//   // B   ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById();
////        if(sl==null){
////        throw new ApiException("service laundry found");
////        }
//
//        Customer c=customerRepository.findCustomersById(CId);
//        if (c == null) {
//            throw new ApiException("Wrong id");
//        }
//
//        Order O = new Order();
//        O.setStreet();
//
//        orderRepository.save(O);
//    }


    public void updateOrder(Integer id,Orders order){
        Orders o=orderRepository.findOrderById(id);
        if(o==null){
            throw new ApiException("order not found");
        }
        o.setDeliveryPrice(order.getDeliveryPrice());
        o.setTotalPrice(order.getTotalPrice());
        o.setNationalAddressOfCustomer(order.getNationalAddressOfCustomer());
        o.setDistrictOfLaundry(order.getDistrictOfLaundry());
        o.setStreetOfLaundry(o.getDistrictOfLaundry());
        o.setRating(order.getRating());
        o.setComment(order.getComment());

        orderRepository.save(o);

    }


    public void deleteOrder(Integer id){
        Orders order=orderRepository.findOrderById(id);
        if(order==null){
            throw new ApiException("order not found");
        }
        orderRepository.delete(order);
    }

    //---------------------------  end CRUD  ---------------------------------

}