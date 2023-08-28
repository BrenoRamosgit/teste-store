package br.com.stoom.store.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Table(name = "product_variation")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ProductVariation extends NewBaseModel<ProductVariation>{
	
	private static final long serialVersionUID = 4104867225028656932L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variation_sequence")
    @SequenceGenerator(name = "product_variation_sequence", sequenceName = "PRODUCT_VARIATION_SEQ", allocationSize=1)
    @Column(nullable = false, name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "promo_price")
	private BigDecimal promoPrice;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "unit_of_measure")
	@Enumerated(EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	@Column(name = "length")
	private Double length;
	
	@Column(name = "width")
	private Double width;
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "unit_of_weight")
	@Enumerated(EnumType.STRING)
	private UnitOfWeight UnitOfWeight;
	
	@Column(name = "weight")
	private Double weight;

	@Column(name="sku")
	private String sku;
	

}
