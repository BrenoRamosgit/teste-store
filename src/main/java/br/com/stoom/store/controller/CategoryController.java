package br.com.stoom.store.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.controller.swagger.CategoryControllerSwagger;
import br.com.stoom.store.dto.request.CategoryRequest;
import br.com.stoom.store.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerSwagger {

	private final CategoryBO categoryService;

	@GetMapping("/")
	public ResponseEntity<List<CategoryResponse>> findAll() {
		List<CategoryResponse> categoryResponse = categoryService.findAll();
		if (!categoryResponse.isEmpty()) {
			return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@Override
	public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
		CategoryResponse categoryResponse = categoryService.create(request);
		return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
		CategoryResponse categoryResponse = categoryService.findCategoryById(id);
		return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request) {
		CategoryResponse categoryResponse = categoryService.update(id, request);
		return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{categoryId}/status")
	@Override
	public ResponseEntity<CategoryResponse> updateCategoryStatus(@PathVariable Long categoryId,
			@RequestParam boolean active) {
		CategoryResponse categoryResponse = categoryService.updateCategoryStatus(categoryId, active);
		return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}

}
