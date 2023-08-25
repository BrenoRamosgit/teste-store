package br.com.stoom.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterRequest {

    private String sku;
    
    private String name;

    private String description;
 
    private GenericRequest categories;

    private GenericRequest brand;
    
	private Boolean active;

}
