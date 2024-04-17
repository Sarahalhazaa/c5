package com.example.mylaundry.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "It must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "It must not be empty")
    @Size(min = 5,message ="You must enter at least 5 characters" )
    @Column(columnDefinition = "varchar(20) not null unique")
    private String userName ;

    @NotEmpty(message ="It must not be empty" )
    @Column(columnDefinition = " varchar(10) not null")
    private String password ;

    @NotEmpty(message ="It must not be empty" )
    @Email
    @Column(columnDefinition = " varchar(40) not null unique ")
    private String email;

    @NotEmpty(message ="It must not be empty" )
    @Column(columnDefinition = " varchar(10) not null unique ")
    private String phoneNumber ;

    @NotNull(message = "It must not be empty")
    @Column(columnDefinition = " int not null")
    private Integer age ;

    @NotEmpty(message ="It must not be empty" )
    @Column(columnDefinition = " varchar(20) not null")
    private String nationality ;

    @NotEmpty(message ="It must not be empty" )
    @Column(columnDefinition = " varchar(10) not null")
    private String drivingLicense;

    private Integer deliveryOrders=0;

    @NotNull(message = "It must not be empty")
    @Column(columnDefinition = " int not null")
    private Integer evaluation;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "driver")
    private Set<Orders> orders;

}
