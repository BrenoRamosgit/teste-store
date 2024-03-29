package br.com.stoom.store.business;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import br.com.stoom.store.exception.BaseException;
import br.com.stoom.store.mapper.CategoryMapper;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryBO implements ICategoryBO {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
   
	@Override
	@Transactional
	public CategoryResponse create(CategoryRequest categoryRequest) {
		Category category = mapper.model(categoryRequest);
		Category savedCategory = categoryRepository.save(category);
		return mapper.response(savedCategory);
	}
	
    @Override
    public List<CategoryResponse> findAll(){
        return mapper.response(categoryRepository.findAll());
    }
    
	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() ->  new BaseException(HttpStatus.NOT_FOUND, String.format("Category with ID %d was not found", id)));
	}
	
	@Override
	public CategoryResponse findCategoryById(Long id) {
		Category category = this.findById(id);
		return mapper.response(category);
	}

	@Override
	@Transactional
	public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
		Category category = this.findById(id);
		mapper.updateProductFromRequest(categoryRequest, category);
		Category updatedCategory = categoryRepository.save(category);
		return mapper.response(updatedCategory);
	}

	
	@Override
	@Transactional
	public void delete(Long id) {
		Category category = this.findById(id);
		categoryRepository.delete(category);
	}
	
	@Override
	@Transactional
	public CategoryResponse updateCategoryStatus(Long id, boolean active) {
		Category category = this.findById(id);
		category.setActive(active);
		return mapper.response(categoryRepository.save(category));
	}
}
