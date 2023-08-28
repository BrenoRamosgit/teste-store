package br.com.stoom.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfWeight {

	KILOGRAM(1L, "Kilogramas");
	
	Long id;
	
	String description;
}
	