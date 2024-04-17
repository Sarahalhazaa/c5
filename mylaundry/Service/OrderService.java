package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Customer;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Repository.CustomerRepository;
import com.example.mylaundry.Repository.OrderRepository;
import com.example.mylaundry.Repository.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ServiceLaundryService serviceLaundryService;
    private final ServiceLaundryRepository serviceLaundryRepository;

    public List<Orders> getAllOrder(){
        return orderRepository.findAll();

    }

    public void addOrder(Integer orderId ,Integer customerId, Integer serviceId , Integer quantity ) {

        Orders order1=orderRepository.findOrderById(orderId);
        if(order1==null){
            throw new ApiException("order not found");
        }

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }

        ServiceLaundry service= serviceLaundryRepository.findServiceLaundryById(serviceId);
        if(service==null){
            throw new ApiException("service laundry found");
        }

        if(order1.getCustomer().getId()==customerId) {


            //  order1.setQuantity(quantity);
            // order1.setCustomerId(customerId);
            //  order1.setBranchId(service.getBranch().getId());
            //  order1.setBranch(service.getBranch());
            // order1.setCustomer(customer1);
            order1.setServicePrice(order1.getServicePrice()+service.getPrice());
            //order1.setDeliveryPrice(order1.getDeliveryPrice());
            order1.setTotalPrice(order1.getServicePrice() + order1.getDeliveryPrice());
           // order1.setStatus("Waiting");
           // order1.setDistrictOfLaundry(service.getBranch().getDistrict());
            //order1.setStreetOfLaundry(service.getBranch().getStreet());
           // order1.setNationalAddressOfCustomer(customer1.getProfile().getNationalAddress());
            //order1.getBranch().setNumberOfOrder(order1.getBranch().getNumberOfOrder() + 1);
            service.setNumberOrder(service.getNumberOrder() + 1);

            orderRepository.save(order1);
        }
    }


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