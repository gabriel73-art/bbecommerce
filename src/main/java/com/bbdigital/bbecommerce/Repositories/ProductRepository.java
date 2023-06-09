package com.bbdigital.bbecommerce.Repositories;

import com.bbdigital.bbecommerce.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {


    Page<Product> findByNameContainingAndCategoryContainingAndDescriptionContainingAndDetailsContaining(String name, String category,String descrption, String details, Pageable pageable);
    Page<Product> findByNameContaining(String name,Pageable pageable);

    Page<Product> findByCategoryContaining(String category,Pageable pageable);
    Page<Product> findByDescriptionContaining(String description,Pageable pageable);

    Page<Product> findByDetailsContaining(String valoration,Pageable pageable);
    long countByNameContainingAndCategoryContaining(String name, String category);
    long countByNameContaining(String name);
    long countByCategoryContaining(String category);
    long countByDescriptionContaining(String description);
    long countByDetailsContaining(String details);


    @Query("select distinct p from Product p where p.stock=0")
    List<Product> getNoExistenceProducts();

}
