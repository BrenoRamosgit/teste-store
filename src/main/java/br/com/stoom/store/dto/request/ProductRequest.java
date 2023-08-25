package br.com.stoom.store.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String sku;
    
    private String name;

    private String description;

 
    private List<GenericRequest> categories;

    @JsonIgnore
    private GenericRequest brand;

    @JsonIgnore
    private PriceRequest price;
    
	private Boolean active;

}
