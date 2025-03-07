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
    @Column(name = "sale_price")
    private Double salePrice;
    private String description;
    private Boolean status;
    @Column(name = "created_at")
    private Date createdAt;
    private Date update_at;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    // Thêm mối quan hệ với bảng PrdImages
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)  // mappedBy xác định thuộc tính bên lớp con
    private List<PrdImages> images;  // Danh sách các hình ảnh của sản phẩm

    // Quan hệ với bảng carts
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Transient  // Không ánh xạ vào DB
    private boolean favorited;

    @Transient  // Không ánh xạ vào DB
    private Integer favoriteId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails; // Một sản phẩm có thể xuất hiện trong nhiều đơn hàng

}
