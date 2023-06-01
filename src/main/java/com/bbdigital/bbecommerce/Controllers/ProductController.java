package com.bbdigital.bbecommerce.Controllers;

import com.bbdigital.bbecommerce.Models.Product;
import com.bbdigital.bbecommerce.Services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    /*@GetMapping("/all_productos")
    public ResponseEntity<Page<Product>> pagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc
    )
    {
        Page<Product> products = productService.pagination(PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
            products = productService.pagination(PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
    }*/
    /*@GetMapping("/admin/all_productos")
    public ResponseEntity<Page<Product>> adminPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc
    )
    {
        Page<Product> products = productService.allPagination(PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
            products = productService.pagination(PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
    }*/
    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc,
            @RequestParam(defaultValue="",required = false) String name,
            @RequestParam(defaultValue="",required = false) String category
    )
    {
        if(!name.isEmpty()||!category.isEmpty()){
            name=name.toUpperCase();
            category=category.toUpperCase();
            Page<Product> products = productService.paginationSearch(name,category,PageRequest.of(page, size, Sort.by(order)));
            if(!asc)
                products = productService.paginationSearch(name,category, PageRequest.of(page, size, Sort.by(order).descending()));
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else if(!name.isEmpty()&&!category.isEmpty()){
            Page<Product> products = productService.allPagination(PageRequest.of(page, size, Sort.by(order)));
            if(!asc)
                products = productService.pagination(PageRequest.of(page, size, Sort.by(order).descending()));
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        Page<Product> products = productService.pagination(PageRequest.of(page, size, Sort.by(order)));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/quantity")
    public int getQuantity(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) double price,
                                                     @RequestParam(required = false) String category) {
        List<Product> le=new ArrayList<>();
        if(!name.isEmpty()||!category.isEmpty()||Double.isNaN(price)){
        System.out.println(productService);
         le = productService.getQuantity(name,category,price);
        }
        else{
            le = productService.getAllProducts();
        }
        return le.size();
    }
    @CrossOrigin
    @GetMapping("/noExistence")
    public ResponseEntity<List<Product>> getNonStockProducts() {
        return (ResponseEntity.ok(productService.getNoStock()));
    }


}
