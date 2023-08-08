package com.bookshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String author;
    private double price;
    private String imageURL;
    private String description;
    private int quantity;
    private  int sold;
    private double votePoint;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryBook categoryBook;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
