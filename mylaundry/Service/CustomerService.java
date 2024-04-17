package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Customer;
import com.example.mylaundry.Model.Driver;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Repository.CustomerRepository;
import com.example.mylaundry.Repository.DriverRepository;
import com.example.mylaundry.Repository.OrderRepository;
import com.example.mylaundry.Repository.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    //• Add new Customer
    public void addCustomer(Customer customers) {

        customerRepository.save(customers);
    }


    //• Update Customer
    public void updateCustomer(Integer id,Customer customers) {
        Customer c=customerRepository.findCustomersById(id);

        if (c == null) {
            throw new ApiException("Wrong id");
        }
        c.setName(customers.getName());
         c.setProfile(customers.getProfile());

        customerRepository.save(c);

    }
    //• Delete Customer
    public void deleteCustomer(Integer id) {
        Customer c=customerRepository.findCustomersById(id);
        if (c == null) {
            throw new ApiException("Wrong id");
        }
        customerRepository.delete(c);
    }
    //---------------------------  end CRUD  ---------------------------------

    //-----------------------   1 endPoint   ------------------------------

    public void order(Integer customerId, Integer serviceId , Integer quantity ) {

        Orders order1 = new Orders();

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }

        ServiceLaundry service=serviceLaundryRepository.findServiceLaundryById(serviceId);
        if(service==null){
            throw new ApiException("service laundry found");
        }

        order1.setQuantity(quantity);
       // order1.setCustomerId(customerId);
      //  order1.setBranchId(service.getBranch().getId());
        order1.setBranch(service.getBranch());
        order1.setCustomer(customer1);
        order1.setServicePrice(service.getPrice());
        order1.setDeliveryPrice(order1.getDeliveryPrice());
        order1.setTotalPrice(service.getPrice() + order1.getDeliveryPrice());
        order1.setStatus("Waiting");
        order1.setDistrictOfLaundry(service.getBranch().getDistrict());
        order1.setStreetOfLaundry(service.getBranch().getStreet());
        order1.setNationalAddressOfCustomer(customer1.getProfile().getNationalAddress());
order1.getBranch().setNumberOfOrder(order1.getBranch().getNumberOfOrder()+1);
service.setNumberOrder(service.getNumberOrder()+1);

        orderRepository.save(order1);

    }

    //-----------------------   2 endPoint   ------------------------------

    public String statusOfOrder(Integer orderId) {
        Orders orders1 = orderRepository.findOrderById(orderId);
        if (orders1 == null) {
            throw new ApiException("order id not found");
        }

        return orders1.getStatus();
    }

    //-----------------------   3 endPoint   ------------------------------

    public List<Orders> currentOrders(Integer customerId) {
        ArrayList<Orders> orders2 = new ArrayList<>();
        Customer c=customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        List<Orders> orders3 = orderRepository.findOrdersByCustomerId(customerId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry") || orders1.getStatus().equalsIgnoreCase("accepted") || orders1.getStatus().equalsIgnoreCase("Waiting") ){
                orders2.add(orders1);
            }}

        return orders2;
    }

    //-----------------------   4 endPoint   ------------------------------

        public List<Orders> previousOrders(Integer customerId) {
        ArrayList<Orders> orders2 = new ArrayList<>();
        Customer c=customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        List<Orders> orders3 = orderRepository.findOrdersByCustomerId(customerId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered") ){
                orders2.add(orders1);
            }}

        return orders2;
    }


    //-----------------------   5 endPoint   ------------------------------

    public void evaluationDriver(Integer customerId, Integer orderId, Double evaluation) {

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }
        Orders orders1 = orderRepository.findOrderById(orderId);
        if (orders1 == null) {
            throw new ApiException(" Order id not found");
        }
        if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
            if (orders1.getCustomer().getId().equals(customerId)){

                Driver driver = driverRepository.findDriversById(orders1.getDriver().getId());

                Double e =  ( (driver.getEvaluation()+evaluation) / driver.getDeliveryOrders());
                driverRepository.updateDriverEvaluationById(driver.getId(),e);
                driverRepository.save(driver);


            }}}

}


