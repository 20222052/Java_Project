package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

@Entity
@Table(name= "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private Double price;
    private Double sale_price;
    private String description;
    private Boolean status;
    private Date created_at;
    private Date update_at;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
}
