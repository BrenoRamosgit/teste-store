package br.com.stoom.store.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.mapper.ProductMapper;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductBO implements IProductBO {

	private final ProductRepository productRepository;
	private final BrandBO brandService;
	private final CategoryBO categoryService;
	private final ProductMapper mapper;

	
	private Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}
	
	@Override
	public List<ProductResponse> findAll() {
		return mapper.response(productRepository.findAll());
	}

	@Override
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
	
	
}
