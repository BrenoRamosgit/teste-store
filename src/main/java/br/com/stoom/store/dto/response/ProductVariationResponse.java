package br.com.stoom.store.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.stoom.store.model.UnitOfMeasure;
import br.com.stoom.store.model.UnitOfWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductVariationResponse {

	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal promoPrice;
	
	private Integer amount;
	
	private UnitOfMeasure unitOfMeasure;
	
	private Double length;
	
	private Double width;
	
	private Double height;
	
	private UnitOfWeight UnitOfWeight;
	
	private Double weight;

	private String sku;
	
}
