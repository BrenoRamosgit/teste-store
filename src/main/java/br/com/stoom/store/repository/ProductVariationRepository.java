package br.com.stoom.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.ProductVariation;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long>{
}