package com.bookshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    @Column
    private String createBy;
    @Column
    private Date createDate;
    @Column
    private String modifiedBy;
    @Column
    private Date modifiedDate;

    @OneToMany(mappedBy = "categoryBook",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public CategoryBook(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CategoryBook(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}

