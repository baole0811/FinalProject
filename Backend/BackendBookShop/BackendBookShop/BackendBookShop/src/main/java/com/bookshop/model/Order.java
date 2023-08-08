package com.bookshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Order extends BaseEntity{

    private String nameRecipient;
    private String address;
    private String phoneNumber;
    private String payment;
    private double totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
