package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name= "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true, length = 100)
    private String name;
    private Boolean status;
    private Date created_at;
    private Date updated_at;

    @OneToMany(mappedBy = "cateId")
    private List<Product> products;

}