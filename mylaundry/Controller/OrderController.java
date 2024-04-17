package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Model.Orders;
import com.example.mylaundry.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vi/order")
@RequiredArgsConstructor
public class OrderController {

    private  final OrderService orderService;


    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrder());

    }

    @PostMapping("/add/{orderId}/{customerId}/{serviceId}")
    public ResponseEntity addOrders(@PathVariable Integer orderId ,@PathVariable Integer customerId, @PathVariable Integer serviceId  ){
        orderService.addOrder(orderId,customerId,serviceId);
        return ResponseEntity.status(200).body(new ApiResponse("Order Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Orders order){
        orderService.updateOrder(id,order);
        return ResponseEntity.status(200).body(new ApiResponse( "order updated"));
    }

    //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("order deleted!"));
    }






}