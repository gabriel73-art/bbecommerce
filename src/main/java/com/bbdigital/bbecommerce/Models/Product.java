package com.bbdigital.bbecommerce.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name="producto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long sku;
    @Column(name="names")
    private String name;
    @Column(name="prices")
    private Double price;
    @Column(name="stocks")
    private Integer stock;
    @Column(name = "images")
    private String img;
    @Column(name="descriptions")
    private String description;
    @Column(name="details")
    private String details;
    @Column(name = "categories")
    private String category;
    @Column(name="valorations")
    private String valoration;

    @Column(name="tags")
    private String tag;





    public Product() {
        super();
    }

    public Product(String name,Double price,Integer stock,String img,String category,String description,String details, String valoration, String tag) {
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.img=img;
        this.category=category;
        this.description=description;
        this.details=details;
        this.valoration=valoration;
        this.tag=tag;
    }


    public Long getId() {
        return sku;
    }

    public void setId(Long id) {
        this.sku = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {return this.stock; }

    public void setStock(int stock) {this.stock = stock; }

    public void subtracExistence() {
        this.stock -= 1;
    }

    public boolean getExistence(){
        if(this.stock==0)
            return false;
        return true;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {return details; }

    public void setDetails(String details) {this.details = details; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {this.category = category; }

    public String getValoration() {
        return valoration;
    }

    public void setValoration(String Valoration) {
        this.valoration = valoration;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


   /* public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return this.quantity * this.price;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public Integer getCantidad() {
        return quantity;
    }*/
}

