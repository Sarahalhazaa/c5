package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Order;
import com.example.mylaundry.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private  final OrderService orderService;


    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrder());

    }

    @PostMapping("/add")
    public ResponseEntity addOrders(@RequestBody @Valid Order order){
        orderService.addOrder(order);
        return ResponseEntity.status(200).body(new ApiResponse("Order Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Order order){
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