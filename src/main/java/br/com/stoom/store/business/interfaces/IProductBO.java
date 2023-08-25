package br.com.stoom.store.business.interfaces;

import java.util.List;

import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;

public interface IProductBO {

    ProductResponse create(ProductRequest productRequest);
    
    ProductResponse findProductById(Long id);
    
    List<ProductResponse> findAll();
    
    ProductResponse update(Long id, ProductRequest productRequest);
    
    ProductResponse  updateProductStatus(Long id, boolean active);
    
    void delete(Long id);
   
    List<ProductResponse> getProductsByCategory(Long categoryId);
    
	List<ProductResponse> getProductsByBrand(Long brandId);

}
