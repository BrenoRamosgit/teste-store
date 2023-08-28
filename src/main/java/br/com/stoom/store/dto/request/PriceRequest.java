package br.com.stoom.store.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class PriceRequest {

    private BigDecimal basePrice;
    private BigDecimal discountedPrice;
    
}
