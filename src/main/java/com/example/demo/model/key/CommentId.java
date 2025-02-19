package com.example.demo.model.key;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentId {
    private Integer customer_id;
    private Integer blog_id;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(customer_id, commentId.customer_id) && Objects.equals(blog_id, commentId.blog_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, blog_id);
    }
}
