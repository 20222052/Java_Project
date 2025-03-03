package com.example.demo.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String query = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        return count == 0;
    }
}