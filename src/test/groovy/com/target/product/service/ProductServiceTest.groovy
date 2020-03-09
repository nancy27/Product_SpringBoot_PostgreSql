package com.target.product.service

import com.target.product.service.domain.CurrentPrice
import com.target.product.service.domain.Item
import com.target.product.service.domain.Product
import com.target.product.service.domain.ProductDescription
import com.target.product.service.domain.ProductVO
import com.target.product.service.entity.ProductEntity
import com.target.product.service.repository.ProductRepository
import com.target.product.service.service.ProductService
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ProductServiceTest extends Specification {
    ProductRepository productRepository=Mock(ProductRepository)
    RestTemplate restTemplate=Mock(RestTemplate)

    ProductService productService=new ProductService(productRepository,restTemplate)

    def "Testing getcurrency when input is valid "() {
        given:
        Integer productId=13234678
        ProductEntity productEntity= new ProductEntity(13234678,13.23,"USD")
        productRepository.findByProductId(productId)>> productEntity
        CurrentPrice currentPrice=new CurrentPrice(13.23,"USD")

        when:
        def result= productService.getProductCurrency(productId)

        then:
        currentPrice.equals(result)
    }

    def "testing getCurrency when product is not found in db "(){
        given:
        Integer productId=13234678
        ProductEntity productEntity= new ProductEntity()
        productRepository.findByProductId(productId)>>productEntity
        CurrentPrice currentPrice=new CurrentPrice()
        when:
        def result = productService.getProductCurrency(productId)
        then:
        currentPrice.equals(result)
    }

    def "testing getCurrency when it throws an exception"(){
        given:
        Integer productId=13234678
        ProductEntity productEntity= new ProductEntity()
        productRepository.findByProductId(productId)>> Exception
        when:
         productService.getProductCurrency(productId)
        then:
        thrown(Exception)
    }

    def "Testing getProductName when product name is found in the External API"(){
        given:
        Integer productId=13234678
        ProductDescription productDescription=new ProductDescription("Big Tv")
        Item item =new Item()
        item.setProductDescription(productDescription)
        ProductVO productVO= new ProductVO()
        productVO.setItem(item)
        Product product=new Product()
        product.setProductVO(productVO)
        restTemplate.getForObject(_,_)>>product

        when:

        def result= productService.getProductName(productId)
        then:
        result == productDescription.getTitle()
    }
    def "Testing getProductName when product name is Not found in the External API"(){
        given:Integer productId=13234678
       Product product=new Product()
        restTemplate.getForObject(_,)>>product
        when:
         productService.getProductName(productId)

        then:
        thrown(Exception)
    }
    def "Testing getProductName when calling External API throws an exception"(){
        given:Integer productId=13234678
        restTemplate.getForObject(_,)>>Exception
        when:
         productService.getProductName(productId)

        then:
        thrown(Exception)
    }
}
