package com.bookshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends BaseEntity{
    private String name;
    private String email;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
