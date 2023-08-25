package br.com.stoom.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategories_IdAndActive(Long categoryId, boolean active);
	List<Product> findByBrand_IdAndActive(Long brandId, boolean active);
}