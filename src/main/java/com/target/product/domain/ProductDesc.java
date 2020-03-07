package com.target.product.domain;

public class ProductDesc {
    private Integer productId;
    private String productName;
    private CurrentPrice currentPrice;

    public ProductDesc() {
    }

    public ProductDesc(Integer productId, String productName, CurrentPrice currentPrice) {
        this.productId = productId;
        this.productName = productName;
        this.currentPrice = currentPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CurrentPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.currentPrice = currentPrice;
    }
}
