package br.com.stoom.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.swagger.ProductControllerSwagger;
import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.request.ProductVariationRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.dto.response.ProductVariationResponse;

@Controller
@RequestMapping("/api/products")
public class ProductController implements ProductControllerSwagger {

    @Autowired
    private ProductBO productService;

    @GetMapping("/")
    @Override
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> products = productService.findAll();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
	@Override
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductResponse createdProduct = productService.create(request);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
	@Override
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        ProductResponse product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
	@Override
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductResponse updatedProduct = productService.update(id, request);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
	@Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	productService.delete(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page")
    @Override
	public ResponseEntity<Page<ProductResponse>> list( ProductFilterRequest filter, Pageable pageable) {
    	 Page<ProductResponse> products = productService.findProductsByFilters(filter, pageable);
         if (products!=null &&  !products.isEmpty()) {
        	 return new ResponseEntity<>(products, HttpStatus.OK);
         } else {
        	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
	}

	@PatchMapping("/{productId}/status")
	public ResponseEntity<ProductResponse> updateProductStatus(@PathVariable Long productId,
	                                                @RequestParam boolean active) {
		 ProductResponse product = productService.updateProductStatus(productId, active);
		 return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}")
	@Override
	public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId) {
	    List<ProductResponse> products = productService.getProductsByCategory(categoryId);
	    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	

	@GetMapping("/brand/{brandId}")
	@Override
	public ResponseEntity<List<ProductResponse>> getProductsByBrand(@PathVariable Long brandId) {
	    List<ProductResponse> products = productService.getProductsByBrand(brandId);
	        return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/{productId}/variations")
	@Override
	public ResponseEntity<ProductResponse> addVariationToProduct(
            @PathVariable Long productId,
            @RequestBody ProductVariationRequest productVariationRequest) {
		ProductResponse updatedProduct = productService.addVariationToProduct(productId, productVariationRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/{productId}/variations")
	@Override
    public ResponseEntity<List<ProductVariationResponse>> getProductVariations(@PathVariable Long productId) {
        List<ProductVariationResponse> variations = productService.getProductVariations(productId);
        return ResponseEntity.ok(variations);
    }

    @DeleteMapping("/{productId}/variations/{variationId}")
	@Override
    public ResponseEntity<Void> deleteVariationFromProduct(
            @PathVariable Long productId,
            @PathVariable Long variationId) {
        productService.deleteVariationFromProduct(productId, variationId);
        return ResponseEntity.noContent().build();
    }
}
