package com.target.product.service.service;

import com.target.product.service.domain.CurrentPrice;
import com.target.product.service.domain.Product;
import com.target.product.service.entity.ProductEntity;
import com.target.product.service.domain.ProductDesc;
import com.target.product.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public ProductDesc getProductDetails(Integer productId) throws Exception {
        if(productId == null || productId.toString().length()!=8){
            throw new Exception("product Id must be Valid one");
        }
        return new ProductDesc(productId,getProductName(productId),getProductCurrency(productId));

    }

    protected CurrentPrice getProductCurrency(Integer productId) throws Exception {
        try{ProductEntity productEntity=productRepository.findByProductId(productId);
        if (productEntity == null) {
            return new CurrentPrice();
        }
        return new CurrentPrice(productEntity.getValue(),productEntity.getCurrency_code());
    }catch(Exception e ){
            throw new Exception("Currency Data cannot be found for Product Id" + productId);
        }
    }


    protected String getProductName(Integer productId) throws Exception {
        try {
            Product product = restTemplate.getForObject("https://redsky.target.com/v2/pdp/tcin/" + productId +
                    "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews, rating_and_review_statistics,question_answer_statistics", Product.class);

            if (product != null) {
                if (product.getProductVO() != null) {
                    if (product.getProductVO().getItem() != null) {
                        if (product.getProductVO().getItem().getProductDescription() != null) {
                            return product.getProductVO().getItem().getProductDescription().getTitle();
                        }
                    }
                }
            }
            throw new Exception("product Id " + productId + "Not Found in the Url");
        }catch(Exception e ){
            throw new Exception("product Id "+ productId + "Not Found in the Url");
        }
    }
}

