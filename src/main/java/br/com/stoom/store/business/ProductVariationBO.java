package br.com.stoom.store.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.IProductVariationBO;
import br.com.stoom.store.exception.BaseException;
import br.com.stoom.store.model.ProductVariation;
import br.com.stoom.store.repository.ProductVariationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductVariationBO implements IProductVariationBO {

	private final ProductVariationRepository productVariationRepository;

	@Override
    public ProductVariation getProductVariationById(Long variationId) {
        return productVariationRepository.findById(variationId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Variation not found"));
    }
	
	@Override
    public void deleteById(Long variationId) {
         productVariationRepository.deleteById(variationId);
    }
	
	@Override
    public void delete(ProductVariation productVariation) {
         productVariationRepository.delete(productVariation);
    }
	
	
}
