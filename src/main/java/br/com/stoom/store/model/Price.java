package br.com.stoom.store.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "price")
//@Entity
public class Price {

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "discounted_price")
    private BigDecimal discountedPrice;
    
    
}
