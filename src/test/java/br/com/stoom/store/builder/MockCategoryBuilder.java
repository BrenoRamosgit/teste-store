package br.com.stoom.store.builder;

import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import br.com.stoom.store.model.Category;

public class MockCategoryBuilder {
	
	public static CategoryRequest buildCategoryRequest() {
        return CategoryRequest.builder()
        .name("Categoria A")
        .build();
    }

    public static CategoryResponse buildCategoryResponse(Long id) {
    	return CategoryResponse.builder()
    	 .id(id)
    	 .active(true)
         .name("Categoria A")
         .build();
    }
    
    public static Category buildCategory(Long id) {
    	return Category.builder()
    	 .id(id)
    	 .active(true)
         .name("Categoria A")
         .build();
    }

}
