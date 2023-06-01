package com.bbdigital.bbecommerce.Services;


import com.bbdigital.bbecommerce.Models.ProductSold;
import com.bbdigital.bbecommerce.Repositories.ProductSoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSoldService {

    @Autowired
    ProductSoldRepository productSoldRepository;

    public List<ProductSold> getAllProductsSolds(){
        return productSoldRepository.findAll();
    }

    public ProductSold create(ProductSold productSold) {
        return productSoldRepository.save(productSold);
    }
}
