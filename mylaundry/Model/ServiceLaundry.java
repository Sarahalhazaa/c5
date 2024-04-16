package com.example.mylaundry.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ServiceLaundry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "category should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    @Pattern(regexp ="^(clothing|home|carpet)$", message = "category must be clothing or home or carpet")
    private String category;
    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    private String name;
    @NotEmpty(message = "service type should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    @Pattern(regexp ="^(laundry|presser|ironAndWash|steamIroning)$", message = "service type must be laundry or presser or ironAndWash or steamIroning")
    private String serviceType;
    @NotNull(message = "price should not be null")
    @Column(columnDefinition = "int not null")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "laundry_id",referencedColumnName = "id")
    @JsonIgnore
    private Laundry laundry;

}