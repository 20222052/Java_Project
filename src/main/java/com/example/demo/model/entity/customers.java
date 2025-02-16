package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class customers {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 100)
        private String name;

        @Column(nullable = false, unique = true, length = 100)
        private String email;

        @Column(nullable = false, length = 100)
        private String phone;

        @Column(length = 100)
        private String address;

        @Column(nullable = false)
        private Integer gender = 0;

        @Column(nullable = false, length = 200)
        private String password;

        @Column()
        private Date emailVerifiedAt;

        @Column()
        private Date createdAt = new Date();

        @Column()
        private Date updatedAt;


}
