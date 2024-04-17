package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Branch;
import com.example.mylaundry.Model.Laundry;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Repository.BranchRepository;
import com.example.mylaundry.Repository.LaundryRepository;
import com.example.mylaundry.Repository.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceLaundryService {

    private final ServiceLaundryRepository serviceLaundryRepository;
    private final LaundryRepository laundryRepository;
    private final BranchRepository branchRepository;

    public List<ServiceLaundry> getAllServiceLaundry(){
        return serviceLaundryRepository.findAll();
    }

    public void addServiceLaundry(Integer branchId,ServiceLaundry serviceLaundry){
        Branch branch1 = branchRepository.findBranchById(branchId);
        if(branchId ==null){
            throw new ApiException("cannot assign");
        }

        serviceLaundry.setBranch(branch1);

        serviceLaundryRepository.save(serviceLaundry);
    }



    public void updateServiceLaundry(Integer id, ServiceLaundry serviceLaundry){
        ServiceLaundry serviceLaundry1=serviceLaundryRepository.findServiceLaundryById(id);
        if(serviceLaundry1==null){
            throw new ApiException("service laundry found");
        }

        serviceLaundry1.setCategory(serviceLaundry.getCategory());
        serviceLaundry1.setName(serviceLaundry.getName());
        serviceLaundry1.setServiceType(serviceLaundry.getServiceType());
        serviceLaundry1.setPrice(serviceLaundry.getPrice());

        serviceLaundryRepository.save(serviceLaundry);
    }

    public void deleteServiceLaundry(Integer id){
        ServiceLaundry serviceLaundry1=serviceLaundryRepository.findServiceLaundryById(id);
        if(serviceLaundry1==null){
            throw new ApiException("service laundry found");
        }
        serviceLaundryRepository.delete(serviceLaundry1);
    }
    //---------------------------  end CRUD  ---------------------------------

//    public void  assignLaundryToServiceLaundry(Integer branchId,Integer serviceLaundry_id){
//        Branch branch1 = branchRepository.findBranchById(branchId);
//        ServiceLaundry serviceLaundry1 =serviceLaundryRepository.findServiceLaundryById(serviceLaundry_id);
//        if(branchId ==null || serviceLaundry1==null){
//            throw new ApiException("cannot assign");
//        }
//
//        serviceLaundry1.setBranch(branch1);
//
//        serviceLaundryRepository.save(serviceLaundry1);
//    }



}