package com.target.product.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("product")
    private ProductVO productVO;

    public Product() {
    }

    public Product(ProductVO productVO) {
        this.productVO = productVO;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }
}
