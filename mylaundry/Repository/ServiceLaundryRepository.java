package com.example.mylaundry.Repository;

import com.example.mylaundry.Model.ServiceLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLaundryRepository extends JpaRepository<ServiceLaundry,Integer> {

    ServiceLaundry findServiceLaundryById(Integer id);

}