package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shop_order", schema = "ecommerce3")
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

    @Column(name = "shipping_address", length = 200)
    private String shippingAddress;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone", length = 12)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "shipping_method")
    private Integer shippingMethod;

    @Column(name = "order_total")
    private Integer orderTotal;

    @Column(name = "order_status")
    private Integer orderStatus;

}