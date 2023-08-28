package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.ProductVariation;

public interface IProductVariationBO {

	ProductVariation getProductVariationById(Long variationId);

	void deleteById(Long variationId);

	void delete(ProductVariation productVariation);


}
