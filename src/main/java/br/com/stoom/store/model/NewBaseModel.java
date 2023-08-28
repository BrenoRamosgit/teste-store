package br.com.stoom.store.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class NewBaseModel<T extends NewBaseModel<T>> implements Serializable{
	
	private static final long serialVersionUID = -1756082210156859058L;

	
	@Embedded
	public DateModel dateModel;

    @Version
    @Column(nullable = false, name = "version")
    public Integer version;
    
	@PrePersist
	public void initializeDates() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
		dateModel = new DateModel(now, null);
	}
	
	@PreUpdate
	public void updateUpdatedAt() {
		dateModel.dt_updated = LocalDateTime.now(ZoneId.of("UTC"));
	}
}