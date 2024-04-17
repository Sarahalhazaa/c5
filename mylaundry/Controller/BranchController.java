package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Branch;
import com.example.mylaundry.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/branch")
@RequiredArgsConstructor
public class BranchController {
    Logger logger = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;

    @GetMapping("/get")
    public ResponseEntity getBranch(){
        logger.info("inside the get all Branch");
        return ResponseEntity.status(200).body(branchService.getBranch());
    }

//    @PostMapping("/add/{merchantId}")
//    public ResponseEntity addBranch(@PathVariable Integer LaundryId , @RequestBody @Valid Branch branch){
//        logger.info("inside add LaundryId");
//        branchService.addBranch(LaundryId,branch);
//        return ResponseEntity.ok().body(new ApiResponse("Branch added"));
//
//    }


    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateBranch(@PathVariable Integer id, @RequestBody @Valid Branch branch){
        logger.info("inside Update Branch");
        branchService.updateBranch(id,branch);

        return ResponseEntity.ok().body(new ApiResponse("Branch Update"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id){
        logger.info("inside delete Branch");
        branchService.deleteBranch(id);
        return ResponseEntity.ok().body(new ApiResponse("Branch Deleted"));

    }
    //---------------------------  end CRUD  ---------------------------------



}
