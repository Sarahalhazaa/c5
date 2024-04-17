package com.example.mylaundry.Repository;

import com.example.mylaundry.Model.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaundryRepository extends JpaRepository<Laundry,Integer> {

    Laundry findLaundryById(Integer id);

}