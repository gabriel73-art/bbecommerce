package com.bbdigital.bbecommerce.common.payload.Specifications;

import com.bbdigital.bbecommerce.Models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> containsName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> containsCategory(String category) {
        return (root, query, builder) -> builder.like(root.get("category"), "%" + category + "%");
    }

    public static Specification<Product> containsDescription(String description) {
        return (root, query, builder) -> builder.like(root.get("description"),"%"+ description + "%");
    }

    public static Specification<Product> containsDetails(String details) {
        return (root, query, builder) -> builder.like(root.get("details"), "%" + details + "%");
    }
}

