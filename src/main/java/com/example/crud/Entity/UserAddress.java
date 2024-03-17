package com.example.crud.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "user_address", schema = "coolmate")
@AllArgsConstructor
public class UserAddress {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Size(max = 500)
    @Column(name = "address_line", length = 500)
    private String addressLine;

    @Size(max = 200)
    @Column(name = "province", length = 200)
    private String province;

    @Size(max = 200)
    @Column(name = "district", length = 200)
    private String district;

    @Size(max = 200)
    @Column(name = "commune", length = 200)
    private String commune;

    @Column(name = "is_default")
    private Integer isDefault;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

}