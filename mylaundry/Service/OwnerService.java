package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Owner;
import com.example.mylaundry.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwner(){
        return ownerRepository.findAll();
    }

    public void addOwner(Owner owner){
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer id,Owner owner){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        o.setName(owner.getName());
        o.setPhoneNumber(owner.getPhoneNumber());
        o.setEmail(owner.getEmail());
        o.setPassword(owner.getPassword());

        ownerRepository.save(o);
    }

    public void deleteOwner(Integer id){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        ownerRepository.delete(o);
    }





}