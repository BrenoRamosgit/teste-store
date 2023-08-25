package br.com.stoom.store.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

	private Long id;

	private String sku;

	private String name;

	private String description;

	private List<CategoryResponse> categories;

	private BrandResponse brand;

 
	private Boolean active;

}
