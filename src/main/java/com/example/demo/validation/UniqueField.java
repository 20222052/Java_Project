package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {
    String message() default "Giá trị đã tồn tại, vui lòng nhập lại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> entityClass();
    String fieldName();
}