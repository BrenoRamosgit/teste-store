package br.com.stoom.store.controller;

import java.util.List;

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

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.controller.swagger.BrandControllerSwagger;
import br.com.stoom.store.dto.request.BrandRequest;
import br.com.stoom.store.dto.response.BrandResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController implements BrandControllerSwagger {

	private final BrandBO brandService;

	@GetMapping("/")
	public ResponseEntity<List<BrandResponse>> findAll() {
		List<BrandResponse> brandResponse = brandService.findAll();
		if (!brandResponse.isEmpty()) {
			return new ResponseEntity<>(brandResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@Override
	public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request) {
		BrandResponse brandResponse = brandService.create(request);
		return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<BrandResponse> findBrandById(@PathVariable Long id) {
		BrandResponse brandResponse = brandService.findBrandById(id);
		return new ResponseEntity<>(brandResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<BrandResponse> update(@PathVariable Long id, @RequestBody BrandRequest request) {
		BrandResponse brandResponse = brandService.update(id, request);
		return new ResponseEntity<>(brandResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		brandService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	@PatchMapping("/{brandId}/status")
	@Override
	public ResponseEntity<BrandResponse> updateBrandStatus(@PathVariable Long brandId, @RequestParam boolean active) {
		BrandResponse brandResponse = brandService.updateBrandStatus(brandId, active);
		return new ResponseEntity<>(brandResponse, HttpStatus.OK);
	}

}
