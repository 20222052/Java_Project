package com.example.demo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "banners")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("'#'")
    @Column(name = "link", nullable = false, length = 100)
    private String link;

    @Size(max = 100)
    @NotNull
    @Column(name = "image", nullable = false, length = 100)
    private String image;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Size(max = 100)
    @ColumnDefault("'top-banner'")
    @Column(name = "position", length = 100)
    private String position;

    @ColumnDefault("0")
    @Column(name = "prioty")
    private Byte prioty;

    @ColumnDefault("0")
    @Column(name = "status")
    private Boolean status;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

}