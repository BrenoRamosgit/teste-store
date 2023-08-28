package br.com.stoom.store.builder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import br.com.stoom.store.dto.request.GenericRequest;
import br.com.stoom.store.dto.request.PriceRequest;
import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.PriceResponse;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.model.Product;

public class MockProductBuilder {

    public static ProductRequest buildProductRequest() {
        return ProductRequest.builder()
        .active(true)
        .name("Produto 1")
        .description("Produto simples")
        .categories(Collections.singletonList(new GenericRequest(1L)))
        .brand(new GenericRequest(1L))
        .sku("12345")
        .price(PriceRequest.builder()
        		.basePrice(new BigDecimal(10))
        		.discountedPrice(new BigDecimal(2))
        		.build())
        .build();
    }

    public static ProductResponse buildProductResponse(Long id) {
    	return ProductResponse.builder()
    	.id(id)
    	 .active(true)
         .name("Produto 1")
         .description("Produto simples")
         .sku("12345")
         .price(PriceResponse.builder()
         		.basePrice(new BigDecimal(10))
         		.discountedPrice(new BigDecimal(2))
         		.build())
         .brand(MockBrandBuilder.buildBrandResponse(1L))
         .categories(Collections.singletonList(MockCategoryBuilder.buildCategoryResponse(1L)))
    	.build();
    }
    
    
    public static Product buildProduct(Long id) {
    	return Product.builder()
    	.id(id)
    	 .active(true)
         .name("Produto 1")
         .description("Produto simples")
         .sku("12345")
        // .price(Price.builder()
       //  		.basePrice(new BigDecimal(10))
       //  		.discountedPrice(new BigDecimal(2))
       //  		.build())
         .brand(MockBrandBuilder.buildBrand(1L))
         .categories(Collections.singletonList(MockCategoryBuilder.buildCategory(1L)))
    	.build();
    }

    public static ProductFilterRequest buildProductFilterRequest() {
       return  ProductFilterRequest.builder()
        .active(true)
        .name("Produto 1")
        .description("Produto simples")
        .sku("12345")
        .categoriesIds(Arrays.asList(1l, 2L))
        .brandsIds(Arrays.asList(1l, 2L))
        .BrandActive(true)
        .categoryActive(true)
        .build();
      
    }

}
