package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "product_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrdImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng ID
    private Integer id;

    @Column(nullable = false, length = 100)  // Cột image là không null và có độ dài tối đa 100 ký tự
    private String image;

    @Column(name = "product_id", nullable = false)
    private Integer productId;  // Liên kết đến sản phẩm (ID sản phẩm)

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean status;  // Trạng thái của ảnh

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;  // Ngày tạo

    @Column(name = "updated_at")
    private Date updatedAt;  // Ngày cập nhật

    // Nếu bạn muốn sử dụng quan hệ giữa product và image, có thể tạo thêm mối quan hệ như sau:
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)  // Khóa ngoại tới bảng products
    private Product product;  // Liên kết ảnh với sản phẩm
}
