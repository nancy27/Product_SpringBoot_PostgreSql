package com.target.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("product_description")
    private ProductDescription productDescription;

    public Item() {
    }

    public Item(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }
}
