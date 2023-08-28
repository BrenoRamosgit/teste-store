package br.com.stoom.store.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "product")
@EqualsAndHashCode(callSuper = false)
@Entity
public class Product  extends NewBaseModel<Product> {
	
    private static final long serialVersionUID = -4123046099548754532L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ", allocationSize=1)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    
	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = {
			@JoinColumn(name = "product_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", nullable = false) })
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "brand_id",  referencedColumnName = "id")
    private Brand brand;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductVariation> variations;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = true;
}