package br.com.stoom.store.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {

    private String sku;
    
    private String name;

    private String description;
 
    private List<GenericRequest> categories;

    private GenericRequest brand;
    
    private List<ProductVariationRequest> variations;

	private Boolean active;

}
