package com.bbdigital.bbecommerce.Controllers;

import com.bbdigital.bbecommerce.Models.Product;
import com.bbdigital.bbecommerce.Models.ProductSold;
import com.bbdigital.bbecommerce.Services.ProductService;
import com.bbdigital.bbecommerce.Services.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {

    @Autowired
    ProductSoldService productSoldService;
    @Autowired
    ProductService productService;

    @PostMapping(value = "/create_sale")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> createSale(@RequestParam("id") Long id) throws Exception {
        Product product=productService.getProductById(id);
        product.subtracExistence();
        ProductSold productSold=new ProductSold();
        productService.update(product.getId(),product);
        productSold.setProduct(product);
        //productSoldService.create(productSold);
        return new ResponseEntity<>(productSoldService.create(productSold), HttpStatus.OK);
    }
}
