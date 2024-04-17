package com.example.mylaundry.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor

@Entity
@NoArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // private Integer BranchId;

   // private Integer customerId;

   // @Column(columnDefinition = "int")
  //  private Integer driverId;
    // ايجكت الخدمة
    //private ServiceLaundry serviceLaundry;

    // @Column(columnDefinition = "double")
    // @NotNull
    private double servicePrice;
    private double deliveryPrice=10;

    //@Column(columnDefinition = "double")
    // @NotNull
    private double totalPrice;

    //@NotEmpty(message = "location should not be empty")
    //@Column(columnDefinition = "varchar(10) not null ")
    // @Column(columnDefinition = "varchar(8) not null")
    private String nationalAddressOfCustomer;
    private String districtOfLaundry;
    private String streetOfLaundry;

    // @NotNull(message = "rating should not be empty")
    //@Column(columnDefinition = "int not null ")
    private Integer rating;

    //@NotEmpty(message = "comment should not be empty")
    // @Column(columnDefinition = "varchar(30) not null ")
    private String comment;

    @Pattern(regexp ="^(Waiting|accepted|received stuff|received to laundry|Delivered)$")
    private String status ;

    private Integer Quantity;

    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driverId",referencedColumnName = "id")
    @JsonIgnore
    private Driver driver;

 @ManyToOne
 @JoinColumn(name = "branchId",referencedColumnName = "id")
 @JsonIgnore
 private Branch branch;

    @ManyToMany
    @JsonIgnore
    private Set<ServiceLaundry> serviceLaundries;








}