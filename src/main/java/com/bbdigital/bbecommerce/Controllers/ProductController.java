package com.bbdigital.bbecommerce.Controllers;

import com.bbdigital.bbecommerce.Models.Product;
import com.bbdigital.bbecommerce.Repositories.ProductRepository;
import com.bbdigital.bbecommerce.Services.ProductService;
import com.bbdigital.bbecommerce.common.payload.Specifications.ProductSpecifications;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;

@Api(tags = "Product Controller EndPoints")
@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    @CrossOrigin
    @PostMapping(value = "/create", consumes = "application/json")
    private ResponseEntity<Product> create(/*@RequestPart(required=false) String[] urls,*/ @RequestBody Product producto) throws IOException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.create(producto));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @ApiOperation(value = "Return all products bundled into response", notes = "Return 204 if not data founded")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "There are not products"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println(productService);
        List<Product> le = productService.getAllProducts();
        return ResponseEntity.ok(le);
    }

    @CrossOrigin
    @GetMapping(value = "get/{id}")
    private ResponseEntity<?> buscar(@PathVariable ("id") Long id){
        return ResponseEntity.ok(productService.findByID(id).get());
    }

    @CrossOrigin
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Product producto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.update(id,producto));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable ("id") Long id){
        if(productService.findByID(id)==null)
            return ResponseEntity.ok(Boolean.FALSE);
        else
            productService.delete(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    /*@GetMapping("/search")
    public ResponseEntity<Page<Product>> buscarProductos(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String details,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        if (name != null && category != null && description != null && details != null) {
            return new ResponseEntity<>(productRepository.findByNameContainingAndCategoryContainingAndDescriptionContainingAndDetailsContaining(name, category,description,details, pageable),HttpStatus.OK);
        } else if (name != null) {
            return new ResponseEntity<>(productRepository.findByNameContaining(name, pageable),HttpStatus.OK);
        } else if (category != null) {
            return new ResponseEntity<>(productRepository.findByCategoryContaining(category, pageable),HttpStatus.OK);
        } else if (description != null) {
            return new ResponseEntity<>(productRepository.findByDescriptionContaining(description, pageable),HttpStatus.OK);
        } else if (details != null) {
            return new ResponseEntity<>(productRepository.findByDetailsContaining(details, pageable),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productRepository.findAll(pageable),HttpStatus.OK);
        }
    }*/

    @GetMapping("/search")
    public Page<Product> buscarProductos(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String details,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Product> spec = Specification.where(null);
        if (name != null) {
            spec = spec.and(ProductSpecifications.containsName(name));
        }
        if (category != null) {
            spec = spec.and(ProductSpecifications.containsCategory(category));
        }
        if (description != null) {
            spec = spec.and(ProductSpecifications.containsDescription(description));
        }
        if (details != null) {
            spec = spec.and(ProductSpecifications.containsDetails(details));
        }
        if (!spec.equals(Specification.where(null))) {
            return productRepository.findAll(spec, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    
    @CrossOrigin
    @GetMapping("/quantity")
    public long getQuantity(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String details
    ) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpecifications.containsName(name));
        }

        if (category != null) {
            spec = spec.and(ProductSpecifications.containsCategory(category));
        }

        if (description != null) {
            spec = spec.and(ProductSpecifications.containsDescription(description));
        }

        if (details != null) {
            spec = spec.and(ProductSpecifications.containsDetails(details));
        }

        if (!spec.equals(Specification.where(null))) {
            return productRepository.count(spec);
        } else {
            return productRepository.count();
        }
    }

    @CrossOrigin
    @GetMapping("/noExistence")
    public ResponseEntity<List<Product>> getNonStockProducts() {
        return (ResponseEntity.ok(productService.getNoStock()));
    }


}
