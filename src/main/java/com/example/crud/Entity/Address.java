package com.example.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "ecommerce3")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "address_line", length = 500)
    private String addressLine;

    @Column(name = "province", length = 200)
    private String province;

    @Column(name = "district", length = 200)
    private String district;

    @Column(name = "commune", length = 200)
    private String commune;

}