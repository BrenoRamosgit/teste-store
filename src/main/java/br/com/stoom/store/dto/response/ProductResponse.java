package br.com.stoom.store.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductResponse {

	private Long id;

	private String sku;

	private String name;

	private String description;

	private List<CategoryResponse> categories;

	private BrandResponse brand;

	private PriceResponse price;
 
	private Boolean active;

}
