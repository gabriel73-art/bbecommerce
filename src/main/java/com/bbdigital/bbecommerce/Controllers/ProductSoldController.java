package com.bbdigital.bbecommerce.Controllers;


import com.bbdigital.bbecommerce.Models.Product;
import com.bbdigital.bbecommerce.Models.ProductSold;
import com.bbdigital.bbecommerce.Services.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSoldController {

    @Autowired
    ProductSoldService productSoldService;

    @GetMapping("/soldProducts")
    public ResponseEntity<List<ProductSold>> getAllSolds() {
        List<ProductSold> list = productSoldService.getAllProductsSolds();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/income")
    public double getIncome() {
        List<ProductSold> list = productSoldService.getAllProductsSolds();
        double income=0.0;
        for(ProductSold p:list){
            income+=p.getProduct().getPrice();
        }
        return income;
    }




}
