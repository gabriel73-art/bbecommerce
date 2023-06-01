package com.bbdigital.bbecommerce.Models;

import javax.persistence.*;

@Entity
@Table (name="soldProducts")
public class ProductSold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long sku;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    public ProductSold() {
        super();
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
