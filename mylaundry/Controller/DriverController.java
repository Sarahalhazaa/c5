package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Driver;
import com.example.mylaundry.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/driver")
@RequiredArgsConstructor
public class DriverController {

    Logger logger = LoggerFactory.getLogger(DriverController.class);

    private final DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity getDriver(){
        logger.info("inside the get all Driver");
        return ResponseEntity.status(200).body(driverService.getDriver());
    }

    @PostMapping("/add")
    public ResponseEntity addDriver(@RequestBody @Valid Driver driver){
        logger.info("inside add Driver");
        driverService.addDriver(driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver added"));

    }
    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateDriver(@PathVariable Integer id, @RequestBody @Valid Driver driver){
        logger.info("inside Update Driver");
        driverService.updateDriver(id,driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable Integer id){
        logger.info("inside delete Driver");
        driverService.deleteDriver(id);
        return ResponseEntity.ok().body(new ApiResponse("Driver Deleted"));

    }

    //---------------------------  end CRUD  ---------------------------------

    //-----------------------   1 endPoint   ------------------------------

    @GetMapping("/getEvaluationOfDriver/{driverId}")
    public ResponseEntity getEvaluationOfDriver(@PathVariable Integer driverId){
        logger.info("inside the get Evaluation Of Driver ");
        return ResponseEntity.status(200).body(driverService.getEvaluationOfDriver(driverId));
    }

    //-----------------------   2 endPoint   ------------------------------

    @GetMapping("/findOrders")
    public ResponseEntity findOrders(){
        logger.info("inside the get order");
        return ResponseEntity.status(200).body(driverService.findOrders());
    }

    //-----------------------   3endPoint   ------------------------------
    @PutMapping("/delivery/{driverId}/{orderId}")
    public ResponseEntity delivery(@PathVariable Integer driverId ,@PathVariable Integer orderId ){
        logger.info("inside Update Driver");
        driverService.delivery(driverId,orderId);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

    //-----------------------   4endPoint   ------------------------------
    @GetMapping("/previousOrders/{driverId}")
    public ResponseEntity previousOrders(@PathVariable Integer driverId){
        logger.info("inside previous Orders");
        return ResponseEntity.status(200).body(driverService.previousOrders(driverId));
    }

    //-----------------------   5 endPoint   ------------------------------

    @GetMapping("/currentOrders/{driverId}")
    public ResponseEntity  currentOrders(@PathVariable Integer driverId){
        logger.info("inside current Orders");
        return ResponseEntity.status(200).body(driverService.currentOrders(driverId));
    }

    //-----------------------   6 endPoint   ------------------------------
    @PutMapping("/updateStatus/{driverId}/{orderId}")
    public ResponseEntity  updateStatus(@PathVariable Integer driverId ,@PathVariable Integer orderId ){
        logger.info("inside Update Driver");
        driverService.updateStatus(driverId,orderId);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

}
