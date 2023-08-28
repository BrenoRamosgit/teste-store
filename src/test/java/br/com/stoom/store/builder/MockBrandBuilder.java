package br.com.stoom.store.builder;

import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import br.com.stoom.store.model.Brand;

public class MockBrandBuilder {
	
	public static BrandRequest buildBrandRequest() {
        return BrandRequest.builder()
        .name("Marca A")
        .acronym("MA")
        .build();
    }

    public static BrandResponse buildBrandResponse(Long id) {
    	return BrandResponse.builder()
    	 .id(id)
    	 .active(true)
         .name("Marca A")
         .acronym("MA")
         .build();
    }
    
    public static Brand buildBrand(Long id) {
    	return Brand.builder()
    	 .id(id)
    	 .active(true)
         .name("Marca A")
         .acronym("MA")
         .build();
    }

}
