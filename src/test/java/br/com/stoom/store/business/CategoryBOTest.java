package br.com.stoom.store.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.stoom.store.builder.MockCategoryBuilder;
import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import br.com.stoom.store.mapper.CategoryMapper;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class) 
public class CategoryBOTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryBO categoryBO;

    private Category category;
    private CategoryResponse categoryResponse;
    private CategoryRequest categoryRequest;
    private Long categoryId = 1L;

    @BeforeEach
    public void setUp() {
        this.category = MockCategoryBuilder.buildCategory(categoryId);
        this.categoryResponse = MockCategoryBuilder.buildCategoryResponse(categoryId);
        this.categoryRequest = MockCategoryBuilder.buildCategoryRequest();
        
    }

    @Test
    public void testCreateCategory() {
        when(mapper.model(categoryRequest)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(mapper.response(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryBO.create(categoryRequest);

        assertEquals(categoryResponse, result);
    }

    @Test
    public void testFindAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.response(anyList())).thenReturn(Collections.emptyList());

        List<CategoryResponse> result = categoryBO.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindCategoryById() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(mapper.response(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryBO.findCategoryById(categoryId);

        assertEquals(categoryResponse, result);
    }

    @Test
    public void testUpdateCategory() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(mapper.response(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryBO.update(categoryId, categoryRequest);

        assertEquals(categoryResponse, result);
    }

    @Test
    public void testUpdateCategoryStatus() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(mapper.response(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryBO.updateCategoryStatus(categoryId, true);

        assertEquals(categoryResponse, result);
        assertTrue(category.getActive());
    }

    @Test
    public void testDeleteCategory() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        categoryBO.delete(categoryId);

        verify(categoryRepository).delete(category);
    }
}
