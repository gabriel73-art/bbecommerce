package com.bbdigital.bbecommerce.Services;


import com.bbdigital.bbecommerce.Models.Product;
import com.bbdigital.bbecommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Product create(Product producto) {
        return productRepository.save(producto);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }


    public Product updateFromUser(Long id, Product producto) {
        Optional<Product> entity = productRepository.findById(id);
        Product p = entity.get();
        producto.setId(id);
        productRepository.save(producto);
        return p;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Product update(Long id, Product producto) {
        Optional<Product> entity = productRepository.findById(id);
        Product p = entity.get();
        producto.setId(id);
        productRepository.save(producto);
        return p;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findByID(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> pagination(Pageable pageable) {
        return productRepository.findAll(pageable);

    }

    //@PreAuthorize("hasRole('ADMIN')")
    public Page<Product> allPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
   /* public Page<Product> paginationSearch(String name, String type, Pageable pageable) {
        return productRepository.findByProductName(name, type,pageable);
    }
    public List<Product> getQuantity(String name, String type,double price) {
        return productRepository.getQuantityProductName(name, type,price);
    }*/

    public List<Product> getNoStock() {
        return productRepository.getNoExistenceProducts();
    }
}

