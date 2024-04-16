package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Customer;
import com.example.mylaundry.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody @Valid Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customers){
        customerService.updateCustomer(id,customers);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted!"));
    }

    //---------------------------  end CRUD  ---------------------------------

    //-----------------------   1 endPoint   ------------------------------

    @GetMapping("/statusOfOrder/{OrderId}")
    public ResponseEntity  statusOfOrder(@PathVariable Integer OrderId){

        return ResponseEntity.status(200).body(customerService.statusOfOrder(OrderId));
    }

    //-----------------------   2 endPoint   ------------------------------
    @GetMapping("/previousOrders/{customerId}")
    public ResponseEntity previousOrders(@PathVariable Integer customerId){

        return ResponseEntity.status(200).body(customerService.previousOrders(customerId));
    }

    //-----------------------   3 endPoint   ------------------------------

    @GetMapping("/currentOrders/{customerId}")
    public ResponseEntity  currentOrders(@PathVariable Integer customerId){

        return ResponseEntity.status(200).body(customerService.currentOrders(customerId));
    }

    //-----------------------   4 endPoint   ------------------------------

    @PutMapping("/evaluationDriver/{customerId}/{orderId}/{evaluation}")
    public ResponseEntity evaluationDriver(@PathVariable Integer customerId, @PathVariable Integer orderId,@PathVariable  Double evaluation){
        customerService.evaluationDriver(customerId,orderId,evaluation);
        return ResponseEntity.status(200).body(new ApiResponse( "evaluation Driver updated"));
    }

}