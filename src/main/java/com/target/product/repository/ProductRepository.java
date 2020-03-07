package com.target.product.repository;

import com.target.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findByProductId(Integer productId);
}
