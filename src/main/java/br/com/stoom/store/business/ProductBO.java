package br.com.stoom.store.business;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.business.interfaces.IProductVariationBO;
import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.request.ProductVariationRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.dto.response.ProductVariationResponse;
import br.com.stoom.store.exception.BaseException;
import br.com.stoom.store.mapper.ProductMapper;
import br.com.stoom.store.mapper.ProductVariationMapper;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductVariation;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.repository.specification.ProductSpecifications;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductBO implements IProductBO {

	private final ProductRepository productRepository;
	private final IBrandBO brandService;
	private final ICategoryBO categoryService;
	private final IProductVariationBO productVariationService;
	private final ProductMapper mapper;
	private final ProductSpecifications productSpecifications;
	private final ProductVariationMapper productVariationMapper;

	private Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() ->  new BaseException(HttpStatus.NOT_FOUND, String.format("Product with ID %d was not found", id)));
				
	}
	
	@Override
	public List<ProductResponse> findAll() {
		return mapper.response(productRepository.findAll());
	}
	
	
	@Override
	public Page<ProductResponse> findProductsByFilters(ProductFilterRequest filter, Pageable pageable) {
		Specification<Product> spec = productSpecifications.withFilters(filter);
        return productRepository.findAll(spec, pageable)
            .map(mapper::response); 
    }
	

	@Override
	@Transactional
	public ProductResponse create(ProductRequest productRequest) {
		Product product = mapper.model(productRequest);
		
		product.setBrand(brandService.findById(product.getBrand().getId()));
		
		product.setCategories(productRequest.getCategories()
				.stream()
				.map(category ->  categoryService.findById(category.getId()))
				.collect(Collectors.toList()));
		
		Product savedProduct = productRepository.save(product);
		return mapper.response(savedProduct);
	}

	
	@Override
	public ProductResponse findProductById(Long id) {
		Product product = this.findById(id);
		return mapper.response(product);
	}

	@Override
	@Transactional
	public ProductResponse update(Long id, ProductRequest productRequest) {
		Product product = this.findById(id);
		mapper.updateProductFromRequest(productRequest, product);
		Product updatedProduct = productRepository.save(product);
		return mapper.response(updatedProduct);
	}

	@Override
	public void delete(Long id) {
		Product product = this.findById(id);
		productRepository.delete(product);
	}

	@Override
	@Transactional
	public ProductResponse updateProductStatus(Long productId, boolean active) {
		Product product = this.findById(productId);
		product.setActive(active);
	    
		return mapper.response(productRepository.save(product));
	}

	@Override
	public List<ProductResponse> getProductsByCategory(Long categoryId) {
		List<Product> products = productRepository.findByCategories_IdAndActive(categoryId, true);
		return mapper.response(products);
	}

	@Override
	public List<ProductResponse> getProductsByBrand(Long brandId) {
		List<Product> products = productRepository.findByBrand_IdAndActive(brandId, true);
		return mapper.response(products);
	}
	
	
	@Override
	@Transactional
	public ProductResponse addVariationToProduct(Long productId, ProductVariationRequest productVariationRequest) {
        Product product = findById(productId);
        ProductVariation productVariation = productVariationMapper.model(productVariationRequest);
        product.getVariations().add(productVariation);
        Product updatedProduct = productRepository.save(product);
        return mapper.response(updatedProduct);
    }

	@Override
    public List<ProductVariationResponse> getProductVariations(Long productId) {
        Product product = findById(productId);
        List<ProductVariation> variations = product.getVariations();
        return productVariationMapper.response(variations);
    }

	@Override
	@Transactional
    public void deleteVariationFromProduct(Long productId, Long variationId) {
        Product product = findById(productId);
        ProductVariation variation = productVariationService.getProductVariationById(variationId);
        product.getVariations().remove(variation);
        productRepository.save(product);
        productVariationService.delete(variation);
    }

	
}
