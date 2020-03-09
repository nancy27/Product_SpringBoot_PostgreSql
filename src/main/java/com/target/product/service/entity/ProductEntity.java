package com.target.product.service.entity;

import javax.persistence.*;
@Entity
@Table(name = "product")
public class ProductEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer productId;
        private Double value;
        private String currency_code;

    public ProductEntity() {
    }

    public ProductEntity(Integer productId, Double value, String currency_code) {
        this.productId = productId;
        this.value = value;
        this.currency_code = currency_code;
    }

    public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }
    }

