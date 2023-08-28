package br.com.stoom.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "brand")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class Brand extends NewBaseModel<Brand>{
	
    private static final long serialVersionUID = -2638423612546513957L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ",  allocationSize = 1)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "acronym")
    private String acronym;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = true;


}
