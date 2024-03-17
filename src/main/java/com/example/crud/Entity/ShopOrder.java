package com.example.crud.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shop_order", schema = "coolmate")
public class ShopOrder {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @Size(max = 200)
    @Column(name = "shipping_address", length = 200)
    private String shippingAddress;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 12)
    @Column(name = "phone", length = 12)
    private String phone;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "shipping_method")
    private Integer shippingMethod;

    @Column(name = "order_total")
    private Integer orderTotal;

    @Column(name = "order_status")
    private Integer orderStatus;

}