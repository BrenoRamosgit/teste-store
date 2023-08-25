package br.com.stoom.store.business.interfaces;

import java.util.List;

import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import br.com.stoom.store.model.Category;

public interface ICategoryBO {

    List<CategoryResponse> findAll();

	Category findById(Long id);

	CategoryResponse create(CategoryRequest categoryRequest);

	void delete(Long id);

	CategoryResponse update(Long id, CategoryRequest categoryRequest);

	CategoryResponse findCategoryById(Long id);
	
	CategoryResponse updateCategoryStatus(Long id, boolean active);

}
