package br.com.stoom.store.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import br.com.stoom.store.model.Category;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

	private final ModelMapper mapper;

	public CategoryResponse response(Category category) {
		return mapper.map(category, CategoryResponse.class);
	}

	public List<CategoryResponse> response(List<Category> categories) {
		return categories.stream().map(this::response).collect(Collectors.toList());
	}

	public Category model(CategoryRequest categoryRequest) {
		return mapper.map(categoryRequest, Category.class);
	}

	public Category updateProductFromRequest(CategoryRequest categoryRequest, Category category) {
		mapper.map(categoryRequest, category);
		return category;
	}

}
