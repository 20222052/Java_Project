package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private int phone;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String token;

    @Column(nullable = false)
    private int customerId;

    @Column(nullable = false)
    private int status;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Order(String name, String email, String phone, String address, String token, Integer id, int i) {
        this.name = name;
        this.email = email;
        this.phone = Integer.parseInt(phone);
        this.address = address;
        this.token = token;
        this.customerId = id;
        this.status = i;
    }
}