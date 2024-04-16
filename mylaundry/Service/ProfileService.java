package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.DTO.ProfileDTO;
import com.example.mylaundry.Model.Customer;
import com.example.mylaundry.Model.Profile;
import com.example.mylaundry.Repository.CustomerRepository;
import com.example.mylaundry.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final CustomerRepository customerRepository;

    public List<Profile> getAllProfiles() {

        return profileRepository.findAll();
    }


    public void addProfiler(ProfileDTO profileDTO){
        Customer customer=customerRepository.findCustomersById(profileDTO.getCustomer_id());
        if(customer==null){
            throw new ApiException("Customer not found");
        }
         Profile profile =new Profile(null,profileDTO.getAge(),profileDTO.getEmail(),profileDTO.getPhoneNumber(),profileDTO.getDistrict(),profileDTO.getNationalAddress(),customer);
         profileRepository.save(profile);
    }



    public void updateProfile(ProfileDTO profileDTO) {
        Profile profile=profileRepository.findProfileById(profileDTO.getCustomer_id());

        if (profile == null) {
            throw new ApiException("Profile not found");
        }
        profile.setAge(profileDTO.getAge());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setDistrict(profileDTO.getDistrict());
        profile.setNationalAddress(profileDTO.getNationalAddress());

        profileRepository.save(profile);


    }

    public void deleteProfile(Integer id) {
        Profile p=profileRepository.findProfileById(id);
        if (p == null) {
            throw new ApiException("Wrong id");
        }
        profileRepository.delete(p);
    }






}