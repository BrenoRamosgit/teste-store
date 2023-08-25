package br.com.stoom.store.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import br.com.stoom.store.model.Brand;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BrandMapper {

	private final ModelMapper mapper;

	public BrandResponse response(Brand brand) {
		return mapper.map(brand, BrandResponse.class);
	}

	public List<BrandResponse> response(List<Brand> brands) {
		return brands.stream().map(this::response).collect(Collectors.toList());
	}

	public Brand model(BrandRequest brandRequest) {
		return mapper.map(brandRequest, Brand.class);
	}

	public Brand updateProductFromRequest(BrandRequest brandRequest, Brand brand) {
		mapper.map(brandRequest, brand);
		return brand;
	}

}
