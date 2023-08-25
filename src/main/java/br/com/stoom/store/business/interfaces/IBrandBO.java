package br.com.stoom.store.business.interfaces;

import java.util.List;

import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import br.com.stoom.store.model.Brand;

public interface IBrandBO {

    List<BrandResponse> findAll();

	Brand findById(Long id);
	
	BrandResponse create(BrandRequest brandRequest);

	void delete(Long id);

	BrandResponse update(Long id, BrandRequest brandRequest);

	BrandResponse findBrandById(Long id);

	BrandResponse updateBrandStatus(Long brandId, boolean active);

}
