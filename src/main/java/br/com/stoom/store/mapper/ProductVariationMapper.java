package br.com.stoom.store.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.stoom.store.dto.request.ProductVariationRequest;
import br.com.stoom.store.dto.response.ProductVariationResponse;
import br.com.stoom.store.model.ProductVariation;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductVariationMapper {

	private final ModelMapper mapper;

	public ProductVariationResponse response(ProductVariation productVariation) {
		return mapper.map(productVariation, ProductVariationResponse.class);
	}

	public List<ProductVariationResponse> response(List<ProductVariation> productVariations) {
		return productVariations.stream().map(this::response).collect(Collectors.toList());
	}

	public ProductVariation model(ProductVariationRequest productVariationRequest) {
		return mapper.map(productVariationRequest, ProductVariation.class);
	}

	public ProductVariation updateProductFromRequest(ProductVariationRequest productVariationyRequest, ProductVariation productVariation) {
		mapper.map(productVariationyRequest, productVariation);
		return productVariation;
	}

}
