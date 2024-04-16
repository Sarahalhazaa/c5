package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Laundry;
import com.example.mylaundry.Model.Owner;
import com.example.mylaundry.Repository.LaundryRepository;
import com.example.mylaundry.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaundryService {

    private final LaundryRepository laundryRepository;
    private final OwnerRepository ownerRepository;

    public List<Laundry> getAllLaundry(){
        return laundryRepository.findAll();
    }

    public void addLaundry(Laundry laundry){
        laundryRepository.save(laundry);
    }

    public void updateLaundry(Integer id,Laundry laundry){
        Laundry l=laundryRepository.findLaundryById(id);
        if(l==null){
            throw new ApiException("laundry found");
        }

        l.setName(laundry.getName());
        l.setCommercialRegistertion(laundry.getCommercialRegistertion());
        l.setPhoneNumber(laundry.getPhoneNumber());
        l.setEmail(laundry.getEmail());

        laundryRepository.save(l);
    }

    public void deleteLaundry(Integer id){
        Laundry l=laundryRepository.findLaundryById(id);
        if(l==null){
            throw new ApiException("laundry found");
        }
        laundryRepository.delete(l);
    }

    public void  assignOwnerToLaundry(Integer owner_id,Integer laundry_id){
        Owner o=ownerRepository.findOwnerById(owner_id);
        Laundry l=laundryRepository.findLaundryById(laundry_id);

        if(o ==null || l==null){
            throw new ApiException("cannot assign");
        }

        l.setOwner(o);

        laundryRepository.save(l);
    }

}