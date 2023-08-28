package br.com.stoom.store.business.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.request.ProductVariationRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.dto.response.ProductVariationResponse;

public interface IProductBO {

    ProductResponse create(ProductRequest productRequest);
    
    ProductResponse findProductById(Long id);
    
    List<ProductResponse> findAll();
    
    ProductResponse update(Long id, ProductRequest productRequest);
    
    ProductResponse  updateProductStatus(Long id, boolean active);
    
    void delete(Long id);
   
    List<ProductResponse> getProductsByCategory(Long categoryId);
    
	List<ProductResponse> getProductsByBrand(Long brandId);

	Page<ProductResponse> findProductsByFilters(ProductFilterRequest filter, Pageable pageable);

	void deleteVariationFromProduct(Long productId, Long variationId);

	List<ProductVariationResponse> getProductVariations(Long productId);

	ProductResponse addVariationToProduct(Long productId, ProductVariationRequest productVariationRequest);

}
