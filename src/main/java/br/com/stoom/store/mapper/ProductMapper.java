package br.com.stoom.store.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.stoom.store.dto.request.ProductRequest;
import br.com.stoom.store.dto.response.ProductResponse;
import br.com.stoom.store.model.Product;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

	private final ModelMapper mapper;



	public ProductResponse response(Product product) {
		return mapper.map(product, ProductResponse.class);
	}

	public List<ProductResponse> response(List<Product> products) {
		return products.stream()
				.map(this::response)
				.collect(Collectors.toList());
	}
	
	public Product model(ProductRequest productRequest) {
		mapper.getConfiguration().getMatchingStrategy().isExact();
	    return mapper.map(productRequest, Product.class);
	}
	
	public Product updateProductFromRequest(ProductRequest productRequest, Product product) {
	  mapper.map(productRequest, product);
	  return product;
	}

	
}
