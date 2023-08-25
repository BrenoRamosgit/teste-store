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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
@Entity
public class Brand {
	
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
