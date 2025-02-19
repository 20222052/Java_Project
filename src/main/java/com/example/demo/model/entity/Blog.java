package com.example.demo.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String link = "#";

    @Column(nullable = false, length = 100)
    private String image;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(length = 100)
    private String position = "top-banner";

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean status = false;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment = new ArrayList<>();
}
