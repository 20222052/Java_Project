package com.example.demo.model.entity;

import com.example.demo.model.entity.Order;
import com.example.demo.validation.UniqueField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Phải điền email")
    @UniqueField(entityClass = Customer.class, fieldName = "email")
    private String email;

    @NotBlank(message = "SĐT không được để trống")
    @UniqueField(entityClass = Customer.class, fieldName = "phone")
    private String phone;

    @NotBlank(message = "Địa chỉ hông được để trống")
    private String address;

    private Integer gender = 0;

    @Size(min = 8,max = 100, message = "Mật khẩu phải nằm trong khoảng 8 đến 100 kí tự")
    private String password;

    @Column()
    private Date emailVerifiedAt;

    @Column()
    private Date createdAt = new Date();

    @Column()
    private Date updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment = new ArrayList<>();

}
