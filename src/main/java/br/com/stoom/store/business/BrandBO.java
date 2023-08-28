package br.com.stoom.store.business;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import br.com.stoom.store.exception.BaseException;
import br.com.stoom.store.mapper.BrandMapper;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandBO implements IBrandBO {

	private final BrandRepository brandRepository;
	private final BrandMapper mapper;

	@Override
	@Transactional
	public BrandResponse create(BrandRequest brandRequest) {
		Brand brand = mapper.model(brandRequest);
		Brand savedbBrand = brandRepository.save(brand);
		return mapper.response(savedbBrand);
	}
	
	@Override
	public List<BrandResponse> findAll() {
		return mapper.response(brandRepository.findAll());
	}
	

	@Override
	public Brand findById(Long id) {
		return brandRepository.findById(id)
				.orElseThrow(() ->  new BaseException(HttpStatus.NOT_FOUND, String.format("Brand with ID %d was not found", id)));
	}

	@Override
	public BrandResponse findBrandById(Long id) {
		Brand brand = this.findById(id);
		return mapper.response(brand);
	}


	@Override
	@Transactional
	public BrandResponse update(Long id, BrandRequest brandRequest) {
		Brand brand = this.findById(id);
		mapper.updateProductFromRequest(brandRequest, brand);
		Brand updatedBrand = brandRepository.save(brand);
		return mapper.response(updatedBrand);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		Brand brand = this.findById(id);
		brandRepository.delete(brand);
	}
	
	@Override
	@Transactional
	public BrandResponse updateBrandStatus(Long brandId, boolean active) {
		Brand brand = this.findById(brandId);
		brand.setActive(active);
	    
		return mapper.response(brandRepository.save(brand));
	}
	

}
