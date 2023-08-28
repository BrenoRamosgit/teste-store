package br.com.stoom.store.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterRequest {

    private String sku;
    
    private String name;

    private String description;
 
    private List<Long> categoriesIds;

    private List<Long> brandsIds;
    
	private Boolean active;
	
	private Boolean categoryActive;
	
	private Boolean BrandActive;

}
