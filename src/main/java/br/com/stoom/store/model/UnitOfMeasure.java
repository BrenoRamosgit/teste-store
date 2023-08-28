package br.com.stoom.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfMeasure {

	CENTIMETER(1L, "Centímetro");
	
	Long id;
	
	String description;
}